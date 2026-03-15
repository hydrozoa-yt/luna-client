// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class IndexedSprite extends Drawable {

	public int anInt1509;
	public boolean aBoolean1510;
	public int anInt1511;
	public int anInt1512;
	public byte aByte1513;
	public int anInt1514;
	public boolean aBoolean1515;
	public byte aByteArray1516[];
	public int pixelColours_1517[];
	public int width_1518;
	public int height_1519;
	public int anInt1520;
	public int anInt1521;
	public int width_1522;
	public int height_1523;

	/**
	 *
	 * @param archive
	 * @param s				name of the sprite
	 * @param length_i		array index of the sprite, somehow
	 */
	public IndexedSprite(Archive archive, String s, int length_i) {
		anInt1509 = 3;
		aBoolean1510 = true;
		anInt1512 = -235;
		aByte1513 = 5;
		anInt1514 = -3539;
		aBoolean1515 = true;
		JagBuffer jagBuffer_data = new JagBuffer(archive.get(s + ".dat"));
		JagBuffer jagBuffer_index = new JagBuffer(archive.get("index.dat"));
		jagBuffer_index.position = jagBuffer_data.getShort();
		System.out.println(s+": index="+jagBuffer_index.position);
		width_1522 = jagBuffer_index.getShort();
		System.out.println(s+": width_1522="+width_1522);
		height_1523 = jagBuffer_index.getShort();
		System.out.println(s+": height_1523="+height_1523);
		int length_j = jagBuffer_index.getByte();
		System.out.println(s+": length_j="+length_j);
		pixelColours_1517 = new int[length_j];
		for (int iterator_k = 0; iterator_k < length_j - 1; iterator_k++) {
			pixelColours_1517[iterator_k + 1] = jagBuffer_index.getTriByte();
			System.out.println(s+": pixelColor["+(iterator_k+1)+"] = "+pixelColours_1517[iterator_k + 1]);
		}

		// doesnt run for titlebox, used for arrays of images
		for (int l = 0; l < length_i; l++) {
			jagBuffer_index.position += 2;
			jagBuffer_data.position += jagBuffer_index.getShort() * jagBuffer_index.getShort();
			jagBuffer_index.position++;
		}

		anInt1520 = jagBuffer_index.getByte();
		System.out.println(s+": unknownByte1="+anInt1520);
		anInt1521 = jagBuffer_index.getByte();
		System.out.println(s+": unknownByte2="+anInt1521);
		width_1518 = jagBuffer_index.getShort();
		System.out.println(s+": width_1518="+width_1518);
		height_1519 = jagBuffer_index.getShort();
		System.out.println(s+": height_1519="+height_1519);
		int typeByte_i1 = jagBuffer_index.getByte();
		System.out.println(s+": typeByte_i1="+typeByte_i1);
		int byteLength_j1 = width_1518 * height_1519;
		aByteArray1516 = new byte[byteLength_j1];
		if (typeByte_i1 == 0) {
			for (int k1 = 0; k1 < byteLength_j1; k1++) {
				aByteArray1516[k1] = jagBuffer_data.getSignedByte();
				/*if (s.equalsIgnoreCase("titlebox")) {
					System.out.println(s + ": byte["+k1+"]=" + aByteArray1516[k1]);
				}*/
			}
			return;
		}
		if (typeByte_i1 == 1) {
			for (int l1 = 0; l1 < width_1518; l1++) {
				for (int i2 = 0; i2 < height_1519; i2++)
					aByteArray1516[l1 + i2 * width_1518] = jagBuffer_data.getSignedByte();

			}

		}
	}

	public void method485(int i) {
		width_1522 /= 2;
		height_1523 /= 2;
		byte abyte0[] = new byte[width_1522 * height_1523];
		int j = 0;
		if (i != 0)
			return;
		for (int k = 0; k < height_1519; k++) {
			for (int l = 0; l < width_1518; l++)
				abyte0[(l + anInt1520 >> 1) + (k + anInt1521 >> 1) * width_1522] = aByteArray1516[j++];

		}

		aByteArray1516 = abyte0;
		width_1518 = width_1522;
		height_1519 = height_1523;
		anInt1520 = 0;
		anInt1521 = 0;
	}

	public void method486(boolean flag) {
		if (width_1518 == width_1522 && height_1519 == height_1523)
			return;
		byte abyte0[] = new byte[width_1522 * height_1523];
		int i = 0;
		for (int j = 0; j < height_1519; j++) {
			for (int k = 0; k < width_1518; k++)
				abyte0[k + anInt1520 + (j + anInt1521) * width_1522] = aByteArray1516[i++];

		}

		aByteArray1516 = abyte0;
		width_1518 = width_1522;
		if (!flag) {
			return;
		} else {
			height_1519 = height_1523;
			anInt1520 = 0;
			anInt1521 = 0;
			return;
		}
	}

	public void method487(int i) {
		byte abyte0[] = new byte[width_1518 * height_1519];
		int j = 0;
		for (int k = 0; k < height_1519; k++) {
			for (int l = width_1518 - 1; l >= 0; l--)
				abyte0[j++] = aByteArray1516[l + k * width_1518];

		}

		aByteArray1516 = abyte0;
		if (i != 0) {
			return;
		} else {
			anInt1520 = width_1522 - width_1518 - anInt1520;
			return;
		}
	}

	public void method488(byte byte0) {
		byte abyte0[] = new byte[width_1518 * height_1519];
		int i = 0;
		if (byte0 != 7)
			aBoolean1515 = !aBoolean1515;
		for (int j = height_1519 - 1; j >= 0; j--) {
			for (int k = 0; k < width_1518; k++)
				abyte0[i++] = aByteArray1516[k + j * width_1518];

		}

		aByteArray1516 = abyte0;
		anInt1521 = height_1523 - height_1519 - anInt1521;
	}

	public void rgbAdjust_489(int blueAdjust_i, int greenAdjust_j, int redAdjust_k, int unused_l) {
		for (int index_i1 = 0; index_i1 < pixelColours_1517.length; index_i1++) {
			int red_j1 = pixelColours_1517[index_i1] >> 16 & 0xff;
			red_j1 += redAdjust_k;
			if (red_j1 < 0)
				red_j1 = 0;
			else if (red_j1 > 255)
				red_j1 = 255;
			int green_k1 = pixelColours_1517[index_i1] >> 8 & 0xff;
			green_k1 += greenAdjust_j;
			if (green_k1 < 0)
				green_k1 = 0;
			else if (green_k1 > 255)
				green_k1 = 255;
			int blue_l1 = pixelColours_1517[index_i1] & 0xff;
			blue_l1 += blueAdjust_i;
			if (blue_l1 < 0)
				blue_l1 = 0;
			else if (blue_l1 > 255)
				blue_l1 = 255;
			pixelColours_1517[index_i1] = (red_j1 << 16) + (green_k1 << 8) + blue_l1;
		}

		if (unused_l == anInt1512)
			;
	}

	public void draw_490(int i, int j, int k) {
		j += anInt1520;
		i += anInt1521;
		while (k >= 0) {
			for (int l = 1; l > 0; l++);
		}
		int i1 = j + i * Drawable.width;
		int j1 = 0;
		int k1 = height_1519;
		int l1 = width_1518;
		int i2 = Drawable.width - l1;
		int j2 = 0;
		if (i < Drawable.startX) {
			int k2 = Drawable.startX - i;
			k1 -= k2;
			i = Drawable.startX;
			j1 += k2 * l1;
			i1 += k2 * Drawable.width;
		}
		if (i + k1 > Drawable.endY)
			k1 -= (i + k1) - Drawable.endY;
		if (j < Drawable.startY) {
			int l2 = Drawable.startY - j;
			l1 -= l2;
			j = Drawable.startY;
			j1 += l2;
			i1 += l2;
			j2 += l2;
			i2 += l2;
		}
		if (j + l1 > Drawable.endX) {
			int i3 = (j + l1) - Drawable.endX;
			l1 -= i3;
			j2 += i3;
			i2 += i3;
		}
		if (l1 <= 0 || k1 <= 0) {
			return;
		} else {
			drawRelated_491(j1, Drawable.pixels, aByteArray1516, j2, pixelColours_1517, k1, l1, i1, false, i2);
			return;
		}
	}

	public void drawRelated_491(int i, int ai[], byte abyte0[], int j, int ai1[], int k, int l, int i1, boolean flag, int j1) {
		int k1 = -(l >> 2);
		l = -(l & 3);
		if (flag)
			anInt1511 = 264;
		for (int l1 = -k; l1 < 0; l1++) {
			for (int i2 = k1; i2 < 0; i2++) {
				byte byte0 = abyte0[i++];
				if (byte0 != 0)
					ai[i1++] = ai1[byte0 & 0xff];
				else
					i1++;
				byte0 = abyte0[i++];
				if (byte0 != 0)
					ai[i1++] = ai1[byte0 & 0xff];
				else
					i1++;
				byte0 = abyte0[i++];
				if (byte0 != 0)
					ai[i1++] = ai1[byte0 & 0xff];
				else
					i1++;
				byte0 = abyte0[i++];
				if (byte0 != 0)
					ai[i1++] = ai1[byte0 & 0xff];
				else
					i1++;
			}

			for (int j2 = l; j2 < 0; j2++) {
				byte byte1 = abyte0[i++];
				if (byte1 != 0)
					ai[i1++] = ai1[byte1 & 0xff];
				else
					i1++;
			}

			i1 += j1;
			i += j;
		}

	}
}
