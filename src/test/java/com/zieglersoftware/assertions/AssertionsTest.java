package com.zieglersoftware.assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class AssertionsTest
{
	@Test
	public void notNullSucceed()
	{
		Exception caughtException = null;
		try
		{
			BigDecimal input = new BigDecimal(1);
			BigDecimal result = Assertions.notNull(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void notNullFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notNull(null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void truSucceed()
	{
		Exception caughtException = null;
		try
		{
			Assertions.tru(true, "x");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void truFail()
	{
		String expectedExceptionMessage = "x y z";
		Exception caughtException = null;
		try
		{
			Assertions.tru(false, "x %s %s", "y", "z");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().equals(expectedExceptionMessage))
			fail("Expected exception message \"" + expectedExceptionMessage + "\". Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void falsSucceed()
	{
		Exception caughtException = null;
		try
		{
			Assertions.fals(false, "x");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void falsFail()
	{
		String expectedExceptionMessage = "x y z";
		Exception caughtException = null;
		try
		{
			Assertions.fals(true, "x %s %s", "y", "z");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().equals(expectedExceptionMessage))
			fail("Expected exception message \"" + expectedExceptionMessage + "\". Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void equalSucceed()
	{
		Exception caughtException = null;
		try
		{
			BigDecimal input = new BigDecimal(1);
			BigDecimal result = Assertions.equal(input, new BigDecimal(1), "x");
			assertSame(input, result);
			input = null;
			result = Assertions.equal(input, null, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void equalFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.equal("a", "b", "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("b"))
			fail("Did not get reference value in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("must be equal"))
			fail("Did not get \"must be equal\" in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.equal(null, "b", "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("b"))
			fail("Did not get reference value in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("must be equal"))
			fail("Did not get \"must be equal\" in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.equal("a", null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("null"))
			fail("Did not get reference value in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("must be equal"))
			fail("Did not get \"must be equal\" in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void notEqualSucceed()
	{
		Exception caughtException = null;
		try
		{
			BigDecimal input = new BigDecimal(1);
			BigDecimal result = Assertions.notEqual(input, new BigDecimal(2), "x");
			assertSame(input, result);
			input = null;
			result = Assertions.notEqual(input, new BigDecimal(2), "x");
			assertSame(input, result);
			input = new BigDecimal(1);
			result = Assertions.notEqual(input, null, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void notEqualFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notEqual("a", "a", "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("b"))
			fail("Did not get reference value in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("must not be equal"))
			fail("Did not get \"must not be equal\" in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEqual(null, null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("b"))
			fail("Did not get reference value in exception message. Got \"" + caughtException.getMessage() + "\"");
		if (!caughtException.getMessage().contains("must not be equal"))
			fail("Did not get \"must not be equal\" in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void stringNotEmptySucceed()
	{
		Exception caughtException = null;
		try
		{
			String input = "a";
			String result = Assertions.notEmpty(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void stringNotEmptyFail()
	{
		Exception caughtException = null;
		try
		{
			String s = null;
			Assertions.notEmpty(s, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmpty("", "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void presentSucceed()
	{
		Exception caughtException = null;
		try
		{
			Optional<BigDecimal> input = Optional.of(new BigDecimal(1));
			BigDecimal result = Assertions.present(input, "x");
			assertSame(input.get(), result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void presentFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.present(null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.present(Optional.empty(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numEqualSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.equal(longInput, 1, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.5;
			double doubleResult = Assertions.equal(1.5, 1.5, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigDecimal bigDecimalInput = new BigDecimal("1.5");
			BigDecimal bigDecimalResult = Assertions.sameValue(bigDecimalInput, new BigDecimal("1.500"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numEqualFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.equal(1, 2, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.equal(1.000001, 1.000002, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.sameValue(new BigDecimal("1.000001"), new BigDecimal("1.000002"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numNotEqualSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.notEqual(longInput, 2, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.000001;
			double doubleResult = Assertions.notEqual(doubleInput, 1.000002, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigDecimal bigDecimalInput = new BigDecimal("1.5");
			BigDecimal bigDecimalResult = Assertions.notSameValue(bigDecimalInput, new BigDecimal("1.6"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numNotEqualFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notEqual(1, 1, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEqual(1.000001, 1.000001, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notSameValue(new BigDecimal("1.000001"), new BigDecimal("1.000001"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numGreaterSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 2;
			long longResult = Assertions.greater(longInput, 1, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.0000002;
			double doubleResult = Assertions.greater(doubleInput, 1.0000001, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigInteger bigIntegerInput = new BigInteger("2");
			BigInteger bigIntegerResult = Assertions.greater(bigIntegerInput, new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);

			BigDecimal bigDecimalInput = new BigDecimal("1.0000002");
			BigDecimal bigDecimalResult = Assertions.greater(bigDecimalInput, new BigDecimal("1.0000001000"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numGreaterFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.greater(1, 1, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.greater(1.000001, 1.000001, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.greater(BigInteger.valueOf(1), BigInteger.valueOf(1), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.greater(new BigDecimal("1.000001000"), new BigDecimal("1.000001"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numNotGreaterSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.notGreater(longInput, 1, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.0000001;
			double doubleResult = Assertions.notGreater(doubleInput, 1.0000001, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigInteger bigIntegerInput = new BigInteger("1");
			BigInteger bigIntegerResult = Assertions.notGreater(bigIntegerInput, new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);

			BigDecimal bigDecimalInput = new BigDecimal("1.0000001000");
			BigDecimal bigDecimalResult = Assertions.notGreater(bigDecimalInput, new BigDecimal("1.0000001"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numNotGreaterFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notGreater(2, 1, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notGreater(1.000002, 1.000001, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notGreater(BigInteger.valueOf(2), BigInteger.valueOf(1), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notGreater(new BigDecimal("1.000002"), new BigDecimal("1.000001000"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void lessSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.less(longInput, 2, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.0000001;
			double doubleResult = Assertions.less(doubleInput, 1.0000002, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigInteger bigIntegerInput = new BigInteger("1");
			BigInteger bigIntegerResult = Assertions.less(bigIntegerInput, new BigInteger("2"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);

			BigDecimal bigDecimalInput = new BigDecimal("1.0000001000");
			BigDecimal bigDecimalResult = Assertions.less(bigDecimalInput, new BigDecimal("1.0000002"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void lessFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.less(1, 1, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.less(1.000001, 1.000001, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.less(BigInteger.valueOf(1), BigInteger.valueOf(1), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.less(new BigDecimal("1.000001"), new BigDecimal("1.000001000"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numNotLessSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.notLess(longInput, 1, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.0000001;
			double doubleResult = Assertions.notLess(doubleInput, 1.0000001, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigInteger bigIntegerInput = new BigInteger("1");
			BigInteger bigIntegerResult = Assertions.notLess(bigIntegerInput, new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);

			BigDecimal bigDecimalInput = new BigDecimal("1.0000001");
			BigDecimal bigDecimalResult = Assertions.notLess(bigDecimalInput, new BigDecimal("1.0000001000"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numNotLessFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notLess(1, 2, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notLess(1.000001, 1.000002, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notLess(BigInteger.valueOf(1), BigInteger.valueOf(2), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notLess(new BigDecimal("1.000001000"), new BigDecimal("1.000002"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void numBetweenSucceed()
	{
		Exception caughtException = null;
		try
		{
			long longInput = 1;
			long longResult = Assertions.between(longInput, 0, 1, "x");
			assertEquals(longInput, longResult);
			longResult = Assertions.between(longInput, 0, 2, "x");
			assertEquals(longInput, longResult);
			longResult = Assertions.between(longInput, 1, 1, "x");
			assertEquals(longInput, longResult);
			longResult = Assertions.between(longInput, 1, 2, "x");
			assertEquals(longInput, longResult);

			double doubleInput = 1.0000001;
			double doubleResult = Assertions.between(doubleInput, 0, 1.0000001, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);
			doubleResult = Assertions.between(doubleInput, 0, 2, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);
			doubleResult = Assertions.between(doubleInput, 1.0000001, 2, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);
			doubleResult = Assertions.between(doubleInput, 1.0000001, 1.0000001, "x");
			assertEquals(doubleInput, doubleResult, 1E-100);

			BigInteger bigIntegerInput = new BigInteger("1");
			BigInteger bigIntegerResult = Assertions.between(bigIntegerInput, new BigInteger("0"), new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);
			bigIntegerResult = Assertions.between(bigIntegerInput, new BigInteger("0"), new BigInteger("2"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);
			bigIntegerResult = Assertions.between(bigIntegerInput, new BigInteger("1"), new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);
			bigIntegerResult = Assertions.between(bigIntegerInput, new BigInteger("1"), new BigInteger("1"), "x");
			assertSame(bigIntegerInput, bigIntegerResult);

			BigDecimal bigDecimalInput = new BigDecimal("1.0000001");
			BigDecimal bigDecimalResult = Assertions.between(bigDecimalInput, new BigDecimal("1.000000000000"), new BigDecimal("1.0000001"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
			bigDecimalResult = Assertions.between(bigDecimalInput, new BigDecimal("1.000000000000"), new BigDecimal("1.0000002"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
			bigDecimalResult = Assertions.between(bigDecimalInput, new BigDecimal("1.0000001"), new BigDecimal("1.0000001"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
			bigDecimalResult = Assertions.between(bigDecimalInput, new BigDecimal("1.0000001"), new BigDecimal("1.0000002"), "x");
			assertSame(bigDecimalInput, bigDecimalResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void numBetweenFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.between(2, 0, 1, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.between(1, 1.000001, 1.000002, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.between(BigInteger.valueOf(0), BigInteger.valueOf(1), BigInteger.valueOf(2), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.between(new BigDecimal("1.000001999999000"), new BigDecimal("1.000002"), new BigDecimal("1.000003"), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void collectionNotEmptySucceed()
	{
		Exception caughtException = null;
		try
		{
			Collection<String> input = Arrays.asList("a");
			Collection<String> result = Assertions.notEmpty(input, "x");
			assertSame(input, result);
			input = Arrays.asList("a");
			result = Assertions.notEmpty(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void collectionNotEmptyFail()
	{
		Exception caughtException = null;
		try
		{
			Collection<Object> c = null;
			Assertions.notEmpty(c, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmpty(new ArrayList<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void allNotNullSucceed()
	{
		Exception caughtException = null;
		try
		{
			Collection<String> input = new ArrayList<>();
			Collection<String> result = Assertions.allNotNull(input, "x");
			assertSame(input, result);
			input = Arrays.asList("a", "");
			result = Assertions.allNotNull(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void allNotNullFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.allNotNull(null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.allNotNull(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void notEmptyAllNotNullSucceed()
	{
		Exception caughtException = null;
		try
		{
			Collection<String> input = Arrays.asList("a", "");
			Collection<String> result = Assertions.notEmptyAllNotNull(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void notEmptyAllNotNullFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notEmptyAllNotNull(null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmptyAllNotNull(new ArrayList<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmptyAllNotNull(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void notEmptyAllNotEmptySucceed()
	{
		Exception caughtException = null;
		try
		{
			Collection<String> input = Arrays.asList("a", "b");
			Collection<String> result = Assertions.notEmptyAllNotEmpty(input, "x");
			assertSame(input, result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void notEmptyAllNotEmptyFail()
	{
		Exception caughtException = null;
		try
		{
			Assertions.notEmptyAllNotEmpty(null, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmptyAllNotEmpty(new ArrayList<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmptyAllNotEmpty(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.notEmptyAllNotEmpty(Arrays.asList("a", ""), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void copyIfAllNotNullSucceed()
	{
		Exception caughtException = null;
		try
		{
			List<String> listInput = new ArrayList<>();
			List<String> listResult = Assertions.copyIfAllNotNull(listInput, "x");
			assertFalse("listInput cannot be same object as listResult", listInput == listResult);
			assertEquals(listInput, listResult);
			listInput = Arrays.asList("a", "");
			listResult = Assertions.copyIfAllNotNull(listInput, "x");
			assertFalse("listInput cannot be same object as listResult", listInput == listResult);
			assertEquals(listInput, listResult);

			Set<String> setInput = new HashSet<>();
			Set<String> setResult = Assertions.copyIfAllNotNull(setInput, "x");
			assertFalse("setInput cannot be same object as setResult", setInput == setResult);
			assertEquals(setInput, setResult);
			setInput = new HashSet<>();
			setInput.add("a");
			setInput.add("");
			setResult = Assertions.copyIfAllNotNull(setInput, "x");
			assertFalse("setInput cannot be same object as setResult", setInput == setResult);
			assertEquals(setInput, setResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void copyIfAllNotNullFail()
	{
		Exception caughtException = null;
		try
		{
			List<Object> input = null;
			Assertions.copyIfAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<Object> input = null;
			Assertions.copyIfAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfAllNotNull(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<String> input = new HashSet<>();
			input.add("a");
			input.add(null);
			Assertions.copyIfAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void copyIfNotEmptyAllNotNullSucceed()
	{
		Exception caughtException = null;
		try
		{
			List<String> listInput = Arrays.asList("a", "");
			List<String> listResult = Assertions.copyIfNotEmptyAllNotNull(listInput, "x");
			assertFalse("listInput cannot be same object as listResult", listInput == listResult);
			assertEquals(listInput, listResult);

			Set<String> setInput = new HashSet<>();
			setInput.add("a");
			setInput.add("");
			Set<String> setResult = Assertions.copyIfNotEmptyAllNotNull(setInput, "x");
			assertFalse("setInput cannot be same object as setResult", setInput == setResult);
			assertEquals(setInput, setResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void copyIfNotEmptyAllNotNullFail()
	{
		Exception caughtException = null;
		try
		{
			List<Object> input = null;
			Assertions.copyIfNotEmptyAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<Object> input = null;
			Assertions.copyIfNotEmptyAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotNull(new ArrayList<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotNull(new HashSet<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotNull(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<String> input = new HashSet<>();
			input.add("a");
			input.add(null);
			Assertions.copyIfNotEmptyAllNotNull(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}

	@Test
	public void copyIfNotEmptyAllNotEmptySucceed()
	{
		Exception caughtException = null;
		try
		{
			List<String> listInput = Arrays.asList("a", "b");
			List<String> listResult = Assertions.copyIfNotEmptyAllNotEmpty(listInput, "x");
			assertFalse("listInput cannot be same object as listResult", listInput == listResult);
			assertEquals(listInput, listResult);

			Set<String> setInput = new HashSet<>();
			setInput.add("a");
			setInput.add("b");
			Set<String> setResult = Assertions.copyIfNotEmptyAllNotEmpty(setInput, "x");
			assertFalse("setInput cannot be same object as setResult", setInput == setResult);
			assertEquals(setInput, setResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			caughtException = e;
		}
		if (caughtException != null)
			fail("Unexpected " + caughtException);
	}

	@Test
	public void copyIfNotEmptyAllNotEmptyFail()
	{
		Exception caughtException = null;
		try
		{
			List<String> input = null;
			Assertions.copyIfNotEmptyAllNotEmpty(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<String> input = null;
			Assertions.copyIfNotEmptyAllNotEmpty(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotEmpty(new ArrayList<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotEmpty(new HashSet<>(), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotEmpty(Arrays.asList("a", null), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<String> input = new HashSet<>();
			input.add("a");
			input.add(null);
			Assertions.copyIfNotEmptyAllNotEmpty(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected NullPointerException");
		if (!(caughtException instanceof NullPointerException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Assertions.copyIfNotEmptyAllNotEmpty(Arrays.asList("a", ""), "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");

		caughtException = null;
		try
		{
			Set<String> input = new HashSet<>();
			input.add("a");
			input.add("");
			Assertions.copyIfNotEmptyAllNotEmpty(input, "x");
		}
		catch (Exception e)
		{
			caughtException = e;
		}
		if (caughtException == null)
			fail("Did not throw expected IllegalStateException");
		if (!(caughtException instanceof IllegalStateException))
			fail("Unexpected " + caughtException);
		if (!caughtException.getMessage().contains("\"x\""))
			fail("Did not get variable name in exception message. Got \"" + caughtException.getMessage() + "\"");
	}
}