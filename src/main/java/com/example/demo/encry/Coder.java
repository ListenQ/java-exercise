package com.example.demo.encry;

//  --------------------------------------------------------
//  shared code
//  --------------------------------------------------------

public abstract class Coder {

	public byte[] output;
    public int op;

    /**
     * Encode/decode another block of input data.  this.output is
     * provided by the caller, and must be big enough to hold all
     * the coded data.  On exit, this.opwill be set to the length
     * of the coded data.
     *
     * @param finish true if this is the final call to process for
     *        this object.  Will finalize the coder state and
     *        include any final bytes in the output.
     *
     * @return true if the input so far is good; false if some
     *         error has been detected in the input stream..
     */
    public abstract boolean process(byte[] input, int offset, int len, boolean finish);

    /**
     * @return the maximum number of bytes a call to process()
     * could produce for the given number of input bytes.  This may
     * be an overestimate.
     */
    public abstract int maxOutputSize(int len);
    
}
