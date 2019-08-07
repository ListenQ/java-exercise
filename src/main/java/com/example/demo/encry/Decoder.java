package com.example.demo.encry;

public class Decoder extends Coder {
	/**
	 * Lookup table for turning bytes into their position in the Base64
	 * alphabet.
	 */
	private static final int DECODE[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1,
			-1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
			-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
			42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, };

	/**
	 * Decode lookup table for the "web safe" variant (RFC 3548 sec. 4) where -
	 * and _ replace + and /.
	 */
	private static final int DECODE_WEBSAFE[] = { -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
			-1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
			12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1,
			-1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
			40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, };

	/** Non-data values in the DECODE arrays. */
	private static final int SKIP = -1;
	private static final int EQUALS = -2;

	/**
	 * States 0-3 are reading through the next input tuple. State 4 is having
	 * read one '=' and expecting exactly one more. State 5 is expecting no more
	 * data or padding characters in the input. State 6 is the error state; an
	 * error has been detected in the input and no future input can "fix" it.
	 */
	private int state; // state number (0 to 6)
	private int value;

	final private int[] alphabet;

	public Decoder(int flags, byte[] output) {
		this.output = output;

		alphabet = ((flags & BASE64.URL_SAFE) == 0) ? DECODE : DECODE_WEBSAFE;
		state = 0;
		value = 0;
	}

	/**
	 * @return an overestimate for the number of bytes {@code len} bytes could
	 *         decode to.
	 */
	public int maxOutputSize(int len) {
		return len * 3 / 4 + 10;
	}

	/**
	 * Decode another block of input data.
	 *
	 * @return true if the state machine is still healthy. false if bad base-64
	 *         data has been detected in the input stream.
	 */
	public boolean process(byte[] input, int offset, int len, boolean finish) {
		if (this.state == 6)
			return false;

		int p = offset;
		len += offset;

		// Using local variables makes the decoder about 12%
		// faster than if we manipulate the member variables in
		// the loop. (Even alphabet makes a measurable
		// difference, which is somewhat surprising to me since
		// the member variable is final.)
		int state = this.state;
		int value = this.value;
		int op = 0;
		final byte[] output = this.output;
		final int[] alphabet = this.alphabet;

		while (p < len) {
			// Try the fast path: we're starting a new tuple and the
			// next four bytes of the input stream are all data
			// bytes. This corresponds to going through states
			// 0-1-2-3-0. We expect to use this method for most of
			// the data.
			//
			// If any of the next four bytes of input are non-data
			// (whitespace, etc.), value will end up negative. (All
			// the non-data values in decode are small negative
			// numbers, so shifting any of them up and or'ing them
			// together will result in a value with its top bit set.)
			//
			// You can remove this whole block and the output should
			// be the same, just slower.
			if (state == 0) {
				while (p + 4 <= len
						&& (value = ((alphabet[input[p] & 0xff] << 18)
								| (alphabet[input[p + 1] & 0xff] << 12)
								| (alphabet[input[p + 2] & 0xff] << 6) | (alphabet[input[p + 3] & 0xff]))) >= 0) {
					output[op + 2] = (byte) value;
					output[op + 1] = (byte) (value >> 8);
					output[op] = (byte) (value >> 16);
					op += 3;
					p += 4;
				}
				if (p >= len)
					break;
			}

			// The fast path isn't available -- either we've read a
			// partial tuple, or the next four input bytes aren't all
			// data, or whatever. Fall back to the slower state
			// machine implementation.

			int d = alphabet[input[p++] & 0xff];

			switch (state) {
			case 0:
				if (d >= 0) {
					value = d;
					++state;
				} else if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;

			case 1:
				if (d >= 0) {
					value = (value << 6) | d;
					++state;
				} else if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;

			case 2:
				if (d >= 0) {
					value = (value << 6) | d;
					++state;
				} else if (d == EQUALS) {
					// Emit the last (partial) output tuple;
					// expect exactly one more padding character.
					output[op++] = (byte) (value >> 4);
					state = 4;
				} else if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;

			case 3:
				if (d >= 0) {
					// Emit the output triple and return to state 0.
					value = (value << 6) | d;
					output[op + 2] = (byte) value;
					output[op + 1] = (byte) (value >> 8);
					output[op] = (byte) (value >> 16);
					op += 3;
					state = 0;
				} else if (d == EQUALS) {
					// Emit the last (partial) output tuple;
					// expect no further data or padding characters.
					output[op + 1] = (byte) (value >> 2);
					output[op] = (byte) (value >> 10);
					op += 2;
					state = 5;
				} else if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;

			case 4:
				if (d == EQUALS) {
					++state;
				} else if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;

			case 5:
				if (d != SKIP) {
					this.state = 6;
					return false;
				}
				break;
			}
		}

		if (!finish) {
			// We're out of input, but a future call could provide
			// more.
			this.state = state;
			this.value = value;
			this.op = op;
			return true;
		}

		// Done reading input. Now figure out where we are left in
		// the state machine and finish up.

		switch (state) {
		case 0:
			// Output length is a multiple of three. Fine.
			break;
		case 1:
			// Read one extra input byte, which isn't enough to
			// make another output byte. Illegal.
			this.state = 6;
			return false;
		case 2:
			// Read two extra input bytes, enough to emit 1 more
			// output byte. Fine.
			output[op++] = (byte) (value >> 4);
			break;
		case 3:
			// Read three extra input bytes, enough to emit 2 more
			// output bytes. Fine.
			output[op++] = (byte) (value >> 10);
			output[op++] = (byte) (value >> 2);
			break;
		case 4:
			// Read one padding '=' when we expected 2. Illegal.
			this.state = 6;
			return false;
		case 5:
			// Read all the padding '='s we expected and no more.
			// Fine.
			break;
		}

		this.state = state;
		this.op = op;
		return true;
	}
}