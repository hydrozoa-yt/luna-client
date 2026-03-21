// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class RgbSprite extends Drawable {

	public int anInt1477;
	public boolean aBoolean1478;
	public int anInt1481;
	public boolean aBoolean1486;
	public boolean aBoolean1487;
	public int pixels_1489[];
	public int width_1490;
	public int height_1491;
	public int anInt1492;
	public int anInt1493;
	public int width_1494;
	public int height_1495;

	public RgbSprite(int width, int height) {
		anInt1477 = -235;
		aBoolean1478 = true;
		anInt1481 = -766;
		aBoolean1486 = false;
		aBoolean1487 = true;
		pixels_1489 = new int[width * height];
		width_1490 = width_1494 = width;
		height_1491 = height_1495 = height;
		anInt1492 = anInt1493 = 0;
	}

	public RgbSprite(Archive archive, String name, int i) {
		anInt1477 = -235;
		aBoolean1478 = true;
		anInt1481 = -766;
		aBoolean1486 = false;
		aBoolean1487 = true;
		JagBuffer dataBuf = new JagBuffer(archive.get(name + ".dat"));
		JagBuffer idxBuf = new JagBuffer(archive.get("index.dat"));
		idxBuf.position = dataBuf.getShort();
		width_1494 = idxBuf.getShort();
		height_1495 = idxBuf.getShort();
		int j = idxBuf.getByte();
		int ai[] = new int[j];
		for (int k = 0; k < j - 1; k++) {
			ai[k + 1] = idxBuf.getTriByte();
			if (ai[k + 1] == 0)
				ai[k + 1] = 1;
		}

		for (int l = 0; l < i; l++) {
			idxBuf.position += 2;
			dataBuf.position += idxBuf.getShort() * idxBuf.getShort();
			idxBuf.position++;
		}

		anInt1492 = idxBuf.getByte();
		anInt1493 = idxBuf.getByte();
		width_1490 = idxBuf.getShort();
		height_1491 = idxBuf.getShort();

		int i1 = idxBuf.getByte();
		int pixelsAmount_j1 = width_1490 * height_1491;

		pixels_1489 = new int[pixelsAmount_j1];
		if (i1 == 0) {
			for (int k1 = 0; k1 < pixelsAmount_j1; k1++)
				pixels_1489[k1] = ai[dataBuf.getByte()];

			return;
		}
		if (i1 == 1) {
			for (int l1 = 0; l1 < width_1490; l1++) {
				for (int i2 = 0; i2 < height_1491; i2++)
					pixels_1489[l1 + i2 * width_1490] = ai[dataBuf.getByte()];

			}

		}
	}

	// used for title.dat in index 0 archive 1 named "title"
	public RgbSprite(byte[] spriteData, Component component) {
		anInt1477 = -235;
		aBoolean1478 = true;
		anInt1481 = -766;
		aBoolean1486 = false;
		aBoolean1487 = true;
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(spriteData);
			MediaTracker mediatracker = new MediaTracker(component);
			mediatracker.addImage(image, 0);
			mediatracker.waitForAll();
			width_1490 = image.getWidth(component);
			height_1491 = image.getHeight(component);
			width_1494 = width_1490;
			height_1495 = height_1491;
			anInt1492 = 0;
			anInt1493 = 0;
			pixels_1489 = new int[width_1490 * height_1491];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, width_1490, height_1491, pixels_1489, 0, width_1490);
			pixelgrabber.grabPixels();
		} catch (Exception _ex) {
			System.out.println("Error converting jpg");
		}
	}

	public void method456(boolean flag) {
		if (flag) {
			return;
		} else {
			Drawable.putPixels(width_1490, height_1491, pixels_1489);
			return;
		}
	}

	public void method457(int i, int j, int k, int l) {
		for (int i1 = 0; i1 < pixels_1489.length; i1++) {
			int j1 = pixels_1489[i1];
			if (j1 != 0) {
				int k1 = j1 >> 16 & 0xff;
				k1 += k;
				if (k1 < 1)
					k1 = 1;
				else if (k1 > 255)
					k1 = 255;
				int l1 = j1 >> 8 & 0xff;
				l1 += j;
				if (l1 < 1)
					l1 = 1;
				else if (l1 > 255)
					l1 = 255;
				int i2 = j1 & 0xff;
				i2 += i;
				if (i2 < 1)
					i2 = 1;
				else if (i2 > 255)
					i2 = 255;
				pixels_1489[i1] = (k1 << 16) + (l1 << 8) + i2;
			}
		}

		if (l != anInt1477)
			aBoolean1487 = !aBoolean1487;
	}

	public void method458(int i) {
		int ai[] = new int[width_1494 * height_1495];
		for (int j = 0; j < height_1491; j++) {
			for (int k = 0; k < width_1490; k++)
				ai[(j + anInt1493) * width_1494 + (k + anInt1492)] = pixels_1489[j * width_1490 + k];

		}

		pixels_1489 = ai;
		width_1490 = width_1494;
		height_1491 = height_1495;
		anInt1492 = 0;
		anInt1493 = 0;
		if (i == 1790)
			;
	}

	public void method459(int y, int x) {
		x += anInt1492;
		y += anInt1493;
		int l = x + y * Drawable.width;
		int i1 = 0;
		int j1 = height_1491;
		int k1 = width_1490;
		int l1 = Drawable.width - k1;
		int i2 = 0;
		if (y < Drawable.startX) {
			int j2 = Drawable.startX - y;
			j1 -= j2;
			y = Drawable.startX;
			i1 += j2 * k1;
			l += j2 * Drawable.width;
		}
		if (y + j1 > Drawable.endY) {
            j1 -= (y + j1) - Drawable.endY;
        }
		if (x < Drawable.startY) {
			int k2 = Drawable.startY - x;
			k1 -= k2;
			x = Drawable.startY;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if (x + k1 > Drawable.endX) {
			int l2 = (x + k1) - Drawable.endX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if (k1 <= 0 || j1 <= 0) {
            return;
        }
		method460(pixels_1489, Drawable.pixels, k1, l1, j1, i1, i2, l, (byte) -39);
	}

	public void method460(int[] src, int[] dest, int i, int j, int k, int l, int i1, int j1, byte byte0) {
		int k1 = -(i >> 2);
		i = -(i & 3);
		for (int l1 = -k; l1 < 0; l1++) {
			for (int i2 = k1; i2 < 0; i2++) {
				dest[j1++] = src[l++];
				dest[j1++] = src[l++];
				dest[j1++] = src[l++];
				dest[j1++] = src[l++];
			}

			for (int j2 = i; j2 < 0; j2++)
				dest[j1++] = src[l++];

			j1 += j;
			l += i1;
		}

	}

	public void method461(int i, int j, int k) {
		j += anInt1492;
		if (k >= 0)
			return;
		i += anInt1493;
		int l = j + i * Drawable.width;
		int i1 = 0;
		int j1 = height_1491;
		int k1 = width_1490;
		int l1 = Drawable.width - k1;
		int i2 = 0;
		if (i < Drawable.startX) {
			int j2 = Drawable.startX - i;
			j1 -= j2;
			i = Drawable.startX;
			i1 += j2 * k1;
			l += j2 * Drawable.width;
		}
		if (i + j1 > Drawable.endY)
			j1 -= (i + j1) - Drawable.endY;
		if (j < Drawable.startY) {
			int k2 = Drawable.startY - j;
			k1 -= k2;
			j = Drawable.startY;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if (j + k1 > Drawable.endX) {
			int l2 = (j + k1) - Drawable.endX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if (k1 <= 0 || j1 <= 0) {
			return;
		} else {
			method462(Drawable.pixels, pixels_1489, 0, i1, l, k1, j1, l1, i2);
			return;
		}
	}

	public void method462(int ai[], int ai1[], int i, int j, int k, int l, int i1, int j1, int k1) {
		int l1 = -(l >> 2);
		l = -(l & 3);
		for (int i2 = -i1; i2 < 0; i2++) {
			for (int j2 = l1; j2 < 0; j2++) {
				i = ai1[j++];
				if (i != 0)
					ai[k++] = i;
				else
					k++;
				i = ai1[j++];
				if (i != 0)
					ai[k++] = i;
				else
					k++;
				i = ai1[j++];
				if (i != 0)
					ai[k++] = i;
				else
					k++;
				i = ai1[j++];
				if (i != 0)
					ai[k++] = i;
				else
					k++;
			}

			for (int k2 = l; k2 < 0; k2++) {
				i = ai1[j++];
				if (i != 0)
					ai[k++] = i;
				else
					k++;
			}

			k += j1;
			j += k1;
		}

	}

	public void method463(int i, int j, int k, int l) {
		j += anInt1492;
		k += anInt1493;
		int i1 = j + k * Drawable.width;
		int j1 = 0;
		if (i != 0)
			return;
		int k1 = height_1491;
		int l1 = width_1490;
		int i2 = Drawable.width - l1;
		int j2 = 0;
		if (k < Drawable.startX) {
			int k2 = Drawable.startX - k;
			k1 -= k2;
			k = Drawable.startX;
			j1 += k2 * l1;
			i1 += k2 * Drawable.width;
		}
		if (k + k1 > Drawable.endY)
			k1 -= (k + k1) - Drawable.endY;
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
			method464(l1, j2, 0, i2, j1, anInt1481, l, i1, k1, Drawable.pixels, pixels_1489);
			return;
		}
	}

	public void method464(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int ai[], int ai1[]) {
		int j2 = 256 - k1;
		for (int k2 = -i2; k2 < 0; k2++) {
			for (int l2 = -i; l2 < 0; l2++) {
				k = ai1[i1++];
				if (k != 0) {
					int i3 = ai[l1];
					ai[l1++] = ((k & 0xff00ff) * k1 + (i3 & 0xff00ff) * j2 & 0xff00ff00)
							+ ((k & 0xff00) * k1 + (i3 & 0xff00) * j2 & 0xff0000) >> 8;
				} else {
					l1++;
				}
			}

			l1 += l;
			i1 += j;
		}

		if (j1 >= 0)
			aBoolean1478 = !aBoolean1478;
	}

	public void method465(int i, int j, int k, int l, int i1, int ai[], int j1, int k1, int l1, int ai1[], int i2) {
		j = 36 / j;
		try {
			int j2 = -i1 / 2;
			int k2 = -k / 2;
			int l2 = (int) (Math.sin(k1 / 326.11000000000001D) * 65536D);
			int i3 = (int) (Math.cos(k1 / 326.11000000000001D) * 65536D);
			l2 = l2 * l1 >> 8;
			i3 = i3 * l1 >> 8;
			int j3 = (l << 16) + (k2 * l2 + j2 * i3);
			int k3 = (i2 << 16) + (k2 * i3 - j2 * l2);
			int l3 = j1 + i * Drawable.width;
			for (i = 0; i < k; i++) {
				int i4 = ai1[i];
				int j4 = l3 + i4;
				int k4 = j3 + i3 * i4;
				int l4 = k3 - l2 * i4;
				for (j1 = -ai[i]; j1 < 0; j1++) {
					Drawable.pixels[j4++] = pixels_1489[(k4 >> 16) + (l4 >> 16) * width_1490];
					k4 += i3;
					l4 -= l2;
				}

				j3 += l2;
				k3 += i3;
				l3 += Drawable.width;
			}

			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public void method466(int i, int j, int k, int l, int i1, int j1, int k1, double d, int l1) {
		if (j1 != -30658)
			return;
		try {
			int i2 = -k1 / 2;
			int j2 = -i1 / 2;
			int k2 = (int) (Math.sin(d) * 65536D);
			int l2 = (int) (Math.cos(d) * 65536D);
			k2 = k2 * i >> 8;
			l2 = l2 * i >> 8;
			int i3 = (j << 16) + (j2 * k2 + i2 * l2);
			int j3 = (l << 16) + (j2 * l2 - i2 * k2);
			int k3 = k + l1 * Drawable.width;
			for (l1 = 0; l1 < i1; l1++) {
				int l3 = k3;
				int i4 = i3;
				int j4 = j3;
				for (k = -k1; k < 0; k++) {
					int k4 = pixels_1489[(i4 >> 16) + (j4 >> 16) * width_1490];
					if (k4 != 0)
						Drawable.pixels[l3++] = k4;
					else
						l3++;
					i4 += l2;
					j4 -= k2;
				}

				i3 += k2;
				j3 += l2;
				k3 += Drawable.width;
			}

			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public void method467(IndexedSprite class50_sub1_sub1_sub3, int i, int j, int k) {
		if (j != -49993)
			return;
		k += anInt1492;
		i += anInt1493;
		int l = k + i * Drawable.width;
		int i1 = 0;
		int j1 = height_1491;
		int k1 = width_1490;
		int l1 = Drawable.width - k1;
		int i2 = 0;
		if (i < Drawable.startX) {
			int j2 = Drawable.startX - i;
			j1 -= j2;
			i = Drawable.startX;
			i1 += j2 * k1;
			l += j2 * Drawable.width;
		}
		if (i + j1 > Drawable.endY)
			j1 -= (i + j1) - Drawable.endY;
		if (k < Drawable.startY) {
			int k2 = Drawable.startY - k;
			k1 -= k2;
			k = Drawable.startY;
			i1 += k2;
			l += k2;
			i2 += k2;
			l1 += k2;
		}
		if (k + k1 > Drawable.endX) {
			int l2 = (k + k1) - Drawable.endX;
			k1 -= l2;
			i2 += l2;
			l1 += l2;
		}
		if (k1 <= 0 || j1 <= 0) {
			return;
		} else {
			method468(l, l1, pixels_1489, k1, Drawable.pixels,
					class50_sub1_sub1_sub3.aByteArray1516, 40303, j1, i1, 0, i2);
			return;
		}
	}

	public void method468(int i, int j, int ai[], int k, int ai1[], byte abyte0[], int l, int i1, int j1, int k1, int l1) {
		int i2 = -(k >> 2);
		if (l != 40303)
			aBoolean1486 = !aBoolean1486;
		k = -(k & 3);
		for (int j2 = -i1; j2 < 0; j2++) {
			for (int k2 = i2; k2 < 0; k2++) {
				k1 = ai[j1++];
				if (k1 != 0 && abyte0[i] == 0)
					ai1[i++] = k1;
				else
					i++;
				k1 = ai[j1++];
				if (k1 != 0 && abyte0[i] == 0)
					ai1[i++] = k1;
				else
					i++;
				k1 = ai[j1++];
				if (k1 != 0 && abyte0[i] == 0)
					ai1[i++] = k1;
				else
					i++;
				k1 = ai[j1++];
				if (k1 != 0 && abyte0[i] == 0)
					ai1[i++] = k1;
				else
					i++;
			}

			for (int l2 = k; l2 < 0; l2++) {
				k1 = ai[j1++];
				if (k1 != 0 && abyte0[i] == 0)
					ai1[i++] = k1;
				else
					i++;
			}

			i += j;
			j1 += l1;
		}

	}
}
