/************************************************************/
/*** Date : Dec, 12th, 2011                               ***/
/*** Description: The implementation of Miller-Rabin      ***/ 
/***              to check if a number is prime or not    ***/
/************************************************************/


import java.math.*;
import java.util.*;

public class MillerRabin
{
	
	public static boolean PrimeTest(BigInteger number)
	{
		
		// do Miller-Rabin for 50 times so that the wrong result possibility can go down to 1 / 2 pow (100)
		for(int i = 0;i<50;i++)
		{
			BigInteger a;
			while(true)
			{
				if((a = new BigInteger(number.bitLength(), new Random(new Date().getTime())))==BigInteger.ZERO)
				{
					continue;
				}
				else
				{
					if(MillerRabinRoud(a,number))
					{
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	private static boolean MillerRabinRoud(BigInteger a, BigInteger n)
	{
		BigInteger nMinusOne = n.subtract(BigInteger.ONE);

		BigInteger d = nMinusOne;
		int s = d.getLowestSetBit();
		d = d.shiftRight(s);
		
	    BigInteger aModePow = a.modPow(d, n);
		
	    if (aModePow.equals(BigInteger.ONE))
		{
			return false;
		}
		
	    for (int i = 0; i < s-1; i++) 
		{
	        if (aModePow.equals(nMinusOne))
			{
				return false;
			}
	        aModePow = aModePow.multiply(aModePow).mod(n);
	    }
		
	    if (aModePow.equals(nMinusOne)) 
		{
			return false;
		}
	    return true;		
	}
}