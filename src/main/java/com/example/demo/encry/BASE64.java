package com.example.demo.encry;


/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.UnsupportedEncodingException;

/**
 * Utilities for encoding and decoding the Base64 representation of
 * binary data.  See RFCs <a
 * href="http://www.ietf.org/rfc/rfc2045.txt">2045</a> and <a
 * href="http://www.ietf.org/rfc/rfc3548.txt">3548</a>.
 * 
 * BASE64
 * 基于Android Open Source Project修改
 * 
 * @author gaozhenhai
 * @since 2013.01.15
 * @version 1.0.0_1
 * 
 */
public class BASE64 {
    /**
     * Default values for encoder/decoder flags.
     */
    public static final int DEFAULT = 0;

    /**
     * Encoder flag bit to omit the padding '=' characters at the end
     * of the output (if any).
     */
    public static final int NO_PADDING = 1;

    /**
     * Encoder flag bit to omit all line terminators (i.e., the output
     * will be on one long line).
     */
    public static final int NO_WRAP = 2;

    /**
     * Encoder flag bit to indicate lines should be terminated with a
     * CRLF pair instead of just an LF.  Has no effect if {@code
     * NO_WRAP} is specified as well.
     */
    public static final int CRLF = 4;

    /**
     * Encoder/decoder flag bit to indicate using the "URL and
     * filename safe" variant of Base64 (see RFC 3548 section 4) where
     * {@code -} and {@code _} are used in place of {@code +} and
     * {@code /}.
     */
    public static final int URL_SAFE = 8;

    /**
     * Flag to pass to {@link org.apache.commons.codec.binary.Base64OutputStream} to indicate that it
     * should not close the output stream it is wrapping when it
     * itself is closed.
     */
    public static final int NO_CLOSE = 16;

    //  --------------------------------------------------------
    //  decoding
    //  --------------------------------------------------------

    /**
     * Decode the Base64-encoded data in input and return the data in
     * a new byte array.
     *
     * <p>The padding '=' characters at the end are considered optional, but
     * if any are present, there must be the correct number of them.
     *
     * @param str    the input String to decode, which is converted to
     *               bytes using the default charset
     * @param flags  controls certain features of the decoded output.
     *               Pass {@code DEFAULT} to decode standard Base64.
     *
     * @throws IllegalArgumentException if the input contains
     * incorrect padding
     */
    public static byte[] decode(String str, int flags) {
        return decode(str.getBytes(), flags);
    }

    /**
     * Decode the Base64-encoded data in input and return the data in
     * a new byte array.
     *
     * <p>The padding '=' characters at the end are considered optional, but
     * if any are present, there must be the correct number of them.
     *
     * @param input the input array to decode
     * @param flags  controls certain features of the decoded output.
     *               Pass {@code DEFAULT} to decode standard Base64.
     *
     * @throws IllegalArgumentException if the input contains
     * incorrect padding
     */
    public static byte[] decode(byte[] input, int flags) {
        return decode(input, 0, input.length, flags);
    }

    /**
     * Decode the Base64-encoded data in input and return the data in
     * a new byte array.
     *
     * <p>The padding '=' characters at the end are considered optional, but
     * if any are present, there must be the correct number of them.
     *
     * @param input  the data to decode
     * @param offset the position within the input array at which to start
     * @param len    the number of bytes of input to decode
     * @param flags  controls certain features of the decoded output.
     *               Pass {@code DEFAULT} to decode standard Base64.
     *
     * @throws IllegalArgumentException if the input contains
     * incorrect padding
     */
    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        // Allocate space for the most data the input could represent.
        // (It could contain less if it contains whitespace, etc.)
        Decoder decoder = new Decoder(flags, new byte[len*3/4]);

        if (!decoder.process(input, offset, len, true)) {
            throw new IllegalArgumentException("bad base-64");
        }

        // Maybe we got lucky and allocated exactly enough output space.
        if (decoder.op == decoder.output.length) {
            return decoder.output;
        }

        // Need to shorten the array, so allocate a new one of the
        // right size and copy.
        byte[] temp = new byte[decoder.op];
        System.arraycopy(decoder.output, 0, temp, 0, decoder.op);
        return temp;
    }

    //  --------------------------------------------------------
    //  encoding
    //  --------------------------------------------------------

    /**
     * Base64-encode the given data and return a newly allocated
     * String with the result.
     *
     * @param input  the data to encode
     * @param flags  controls certain features of the encoded output.
     *               Passing {@code DEFAULT} results in output that
     *               adheres to RFC 2045.
     */
    public static String encodeToString(byte[] input, int flags) {
        try {
            return new String(encode(input, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            // US-ASCII is guaranteed to be available.
            throw new AssertionError(e);
        }
    }

    /**
     * Base64-encode the given data and return a newly allocated
     * String with the result.
     *
     * @param input  the data to encode
     * @param offset the position within the input array at which to
     *               start
     * @param len    the number of bytes of input to encode
     * @param flags  controls certain features of the encoded output.
     *               Passing {@code DEFAULT} results in output that
     *               adheres to RFC 2045.
     */
    public static String encodeToString(byte[] input, int offset, int len, int flags) {
        try {
            return new String(encode(input, offset, len, flags), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            // US-ASCII is guaranteed to be available.
            throw new AssertionError(e);
        }
    }

    /**
     * Base64-encode the given data and return a newly allocated
     * byte[] with the result.
     *
     * @param input  the data to encode
     * @param flags  controls certain features of the encoded output.
     *               Passing {@code DEFAULT} results in output that
     *               adheres to RFC 2045.
     */
    public static byte[] encode(byte[] input, int flags) {
        return encode(input, 0, input.length, flags);
    }

    /**
     * Base64-encode the given data and return a newly allocated
     * byte[] with the result.
     *
     * @param input  the data to encode
     * @param offset the position within the input array at which to
     *               start
     * @param len    the number of bytes of input to encode
     * @param flags  controls certain features of the encoded output.
     *               Passing {@code DEFAULT} results in output that
     *               adheres to RFC 2045.
     */
    public static byte[] encode(byte[] input, int offset, int len, int flags) {
        Encoder encoder = new Encoder(flags, null);

        // Compute the exact length of the array we will produce.
        int output_len = len / 3 * 4;

        // Account for the tail of the data and the padding bytes, if any.
        if (encoder.do_padding) {
            if (len % 3 > 0) {
                output_len += 4;
            }
        } else {
            switch (len % 3) {
                case 0: break;
                case 1: output_len += 2; break;
                case 2: output_len += 3; break;
            }
        }

        // Account for the newlines, if any.
        if (encoder.do_newline && len > 0) {
            output_len += (((len-1) / (3 * Encoder.LINE_GROUPS)) + 1) *
                (encoder.do_cr ? 2 : 1);
        }

        encoder.output = new byte[output_len];
        encoder.process(input, offset, len, true);

        assert encoder.op == output_len;

        return encoder.output;
    }

    private BASE64() { }   // don't instantiate
    
    /**
     * BASE64 解码
     * 
     * @param str
     * @return String
     */
    public static byte[] decode(String str) {
    	return decode(str, DEFAULT);
    }
    
    /**
     * BASE64 编码
     * 
     * @param bytes
     * @return String
     */
    public static String encode(byte[] bytes) {
    	return encodeToString(bytes, DEFAULT);
    }
}
