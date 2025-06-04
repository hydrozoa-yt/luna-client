// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class ModelRelated41 {

	public int internalInt697;
	public int anIntArray698[];
	public int anIntArrayArray699[][];

	public ModelRelated41(JagBuffer class50_sub1_sub2, int i) {
		internalInt697 = class50_sub1_sub2.getByte();
		if (i != 0)
			throw new NullPointerException();
		anIntArray698 = new int[internalInt697];
		anIntArrayArray699 = new int[internalInt697][];
		for (int j = 0; j < internalInt697; j++)
			anIntArray698[j] = class50_sub1_sub2.getByte();

		for (int k = 0; k < internalInt697; k++) {
			int l = class50_sub1_sub2.getByte();
			anIntArrayArray699[k] = new int[l];
			for (int i1 = 0; i1 < l; i1++)
				anIntArrayArray699[k][i1] = class50_sub1_sub2.getByte();

		}

	}
}
