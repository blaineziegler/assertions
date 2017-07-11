package com.zieglersoftware.assertions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class Assertions
{
	// Do not instantiate
	private Assertions()
	{
	}

	/**
	 * Asserts that the given object is not null. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static <T> T notNull(T object, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (object == null)
			throw new NullPointerException("Variable \"" + variableName + "\" cannot be null");
		return object;
	}

	/**
	 * Asserts that the given boolean is true.
	 * <p>
	 * {@code exceptionMessageWithPlaceholders}, along with the (optional) {@code placeholderValues}, will be used as the exception message
	 * if {@code test} is false. {@code exceptionMessageWithPlaceholders} should be formatted exactly the same way as in
	 * {@link String#format(String, Object...)}, e.g., %d to represent a long or integer, and %s to represent a string.
	 */
	public static void tru(boolean test, String exceptionMessageWithPlaceholders, Object... placeholderValues)
	{
		if (exceptionMessageWithPlaceholders == null)
			throw new NullPointerException("exceptionMessageWithPlaceholders cannot be null");
		if (placeholderValues == null)
			throw new NullPointerException("placeholderValues cannot be null");
		String combinedExceptionMessage;
		try
		{
			combinedExceptionMessage = String.format(exceptionMessageWithPlaceholders, placeholderValues);
		}
		catch (IllegalFormatException e)
		{
			throw new IllegalArgumentException("exceptionMessageWithPlaceholders and placeholderValues are illegally formatted", e);
		}
		if (!test)
			throw new IllegalStateException(combinedExceptionMessage);
	}

	/**
	 * Asserts that the given boolean is false.
	 * <p>
	 * {@code exceptionMessageWithPlaceholders}, along with the (optional) {@code placeholderValues}, will be used as the exception message
	 * if {@code test} is true. {@code exceptionMessageWithPlaceholders} should be formatted exactly the same way as in
	 * {@link String#format(String, Object...)}, e.g., %d to represent a long or integer, and %s to represent a string.
	 */
	public static void fals(boolean test, String exceptionMessageWithPlaceholders, Object... placeholderValues)
	{
		if (exceptionMessageWithPlaceholders == null)
			throw new NullPointerException("exceptionMessageWithPlaceholders cannot be null");
		if (placeholderValues == null)
			throw new NullPointerException("placeholderValues cannot be null");
		String combinedExceptionMessage;
		try
		{
			combinedExceptionMessage = String.format(exceptionMessageWithPlaceholders, placeholderValues);
		}
		catch (IllegalFormatException e)
		{
			throw new IllegalArgumentException("exceptionMessageWithPlaceholders and placeholderValues are illegally formatted", e);
		}
		if (test)
			throw new IllegalStateException(combinedExceptionMessage);
	}

	/**
	 * Asserts that the given object is equal to the given reference value. If both the given object and the reference value are
	 * null, the assertions passes. If one is null but the other is not, the assertion fails. If both are non-null,
	 * {@code object.equals(referenceVal)} is used to make the comparison. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the object being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static <T> T equal(T object, Object referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (object == null)
		{
			if (referenceVal == null)
				return object;
			else
				throw new IllegalStateException(
					"Variable \"" + variableName + "\" must be equal to " + referenceVal + ". Was null");
		}
		else if (!object.equals(referenceVal))
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be equal to " + referenceVal + ". Was " + object);
		return object;
	}

	/**
	 * Asserts that the given object is not equal to the given reference value. If both the given object and the reference value are
	 * null, the assertions fails. If one is null but the other is not, the assertion passes. If both are non-null,
	 * {@code object.equals(referenceVal)} is used to make the comparison. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the object being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static <T> T notEqual(T object, Object referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (object == null)
		{
			if (referenceVal == null)
				throw new IllegalStateException(
					"Variable \"" + variableName + "\" must not be equal to the given reference value. Both are null");
			else
				return object;
		}
		else if (object.equals(referenceVal))
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be equal to " + referenceVal);
		return object;
	}

	/**
	 * Asserts that the given string is not null and not empty. Returns the given string if the assertion succeeds.
	 * <p>
	 * {@code stringName} should be the name of the string being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static String notEmpty(String string, String stringName)
	{
		if (stringName == null)
			throw new NullPointerException("stringName cannot be null. This is about the name of the string, not the string itself!");
		if (string == null)
			throw new NullPointerException("String \"" + stringName + "\" cannot be null");
		if (string.isEmpty())
			throw new IllegalStateException("String \"" + stringName + "\" cannot be empty");
		return string;
	}

	/**
	 * Asserts that the given {@code Optional} is not null and is {@code present}. Returns the present object, i.e.,
	 * {@code optionalObject.get()}} if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static <T> T present(Optional<T> optionalObject, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (optionalObject == null)
			throw new NullPointerException("Variable \"" + variableName + "\" cannot be null");
		if (!optionalObject.isPresent())
			throw new IllegalStateException("Variable \"" + variableName + "\" must be present");
		return optionalObject.get();
	}

	/**
	 * Asserts that the given value is equal to the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long equal(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val != referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be equal to " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is equal to the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * <b>Note that exact equality comparison of doubles can give suprising results, so use with care.</b>
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double equal(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val != referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be equal to " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is the same, <b>ignoring scale,</b> as the given reference value. In other words,
	 * comparison is done using {@link BigDecimal#compareTo(BigDecimal)} rather than {@link BigDecimal#equals(Object)}.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * For {@code BigDecimal} comparison based on {@code equals} rather than {@code compareTo},
	 * use {@link #equal(Object, Object, String)}.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal sameValue(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) != 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be equal to " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is not equal to the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long notEqual(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val == referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be equal to " + referenceVal);
		return val;
	}

	/**
	 * Asserts that the given value is not equal to the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * <b>Note that exact equality comparison of doubles can give suprising results, so use with care.</b>
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double notEqual(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val == referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be equal to " + referenceVal);
		return val;
	}

	/**
	 * Asserts that the given value is not the same, <b>ignoring scale,</b> as the given reference value. In other words,
	 * comparison is done using {@link BigDecimal#compareTo(BigDecimal)} rather than {@link BigDecimal#equals(Object)}.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * For {@code BigDecimal} comparison based on {@code equals} rather than {@code compareTo},
	 * use {@link #notEqual(Object, Object, String)}.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal notSameValue(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) == 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be equal to " + referenceVal);
		return val;
	}

	/**
	 * Asserts that the given value is greater than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long greater(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val <= referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is greater than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double greater(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val <= referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is greater than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigInteger greater(BigInteger val, BigInteger referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) <= 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is greater than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal greater(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) <= 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is not greater than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long notGreater(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val > referenceVal)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is not greater than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double notGreater(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val > referenceVal)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is not greater than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigInteger notGreater(BigInteger val, BigInteger referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) > 0)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is not greater than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal notGreater(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) > 0)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be greater than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is less than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long less(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val >= referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is less than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double less(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val >= referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is less than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigInteger less(BigInteger val, BigInteger referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) >= 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is less than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal less(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) >= 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is not less than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long notLess(long val, long referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val < referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is not less than the given reference value. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double notLess(double val, double referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val < referenceVal)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is not less than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigInteger notLess(BigInteger val, BigInteger referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) < 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference value are not null
	 * and that the value is not less than the reference value.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal notLess(BigDecimal val, BigDecimal referenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(referenceVal) < 0)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be less than " + referenceVal + ". Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is between the two given reference values, inclusive, i.e.,
	 * {@code lowReferenceVal <= val <= highReferenceVal}. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static long between(long val, long lowReferenceVal, long highReferenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val < lowReferenceVal || val > highReferenceVal)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be between " +
					lowReferenceVal + " and " + highReferenceVal + ", inclusive. Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value is between the two given reference values, inclusive, i.e.,
	 * {@code lowReferenceVal <= val <= highReferenceVal}. Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static double between(double val, double lowReferenceVal, double highReferenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val < lowReferenceVal || val > highReferenceVal)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be between " +
					lowReferenceVal + " and " + highReferenceVal + ", inclusive. Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference values are not null
	 * and that the value is between the two reference values, inclusive, i.e.,
	 * {@code lowReferenceVal <= val <= highReferenceVal}.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigInteger between(BigInteger val, BigInteger lowReferenceVal, BigInteger highReferenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(lowReferenceVal) < 0 || val.compareTo(highReferenceVal) > 0)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be between " +
					lowReferenceVal + " and " + highReferenceVal + ", inclusive. Was " + val);
		return val;
	}

	/**
	 * Asserts that the given value and reference values are not null
	 * and that the value is between the two reference values, inclusive, i.e.,
	 * {@code lowReferenceVal <= val <= highReferenceVal}.
	 * Returns the given value if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static BigDecimal between(BigDecimal val, BigDecimal lowReferenceVal, BigDecimal highReferenceVal, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (val.compareTo(lowReferenceVal) < 0 || val.compareTo(highReferenceVal) > 0)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be between " +
					lowReferenceVal + " and " + highReferenceVal + ", inclusive. Was " + val);
		return val;
	}

	/**
	 * Asserts that the given {@code Collection} is not null and not empty.
	 * Returns the given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested.
	 * It will be included in the exception message for debugging purposes.
	 */
	public static <T extends Collection<?>> T notEmpty(T collection, String collectionName)
	{
		if (collectionName == null)
			throw new NullPointerException(
				"collectionName cannot be null. This is about the name of the collection, not the collection itself!");
		if (collection == null)
			throw new NullPointerException("Collection \"" + collectionName + "\" cannot be null");
		if (collection.isEmpty())
			throw new IllegalStateException("Collection \"" + collectionName + "\" cannot be empty");
		return collection;
	}

	/**
	 * Asserts that the given {@code Collection} is not null and that none of its elements are null.
	 * Returns the given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested.
	 * It will be included in the exception message for debugging purposes.
	 * 
	 * @see #copyIfAllNotNull(List, String)
	 * @see #copyIfAllNotNull(Set, String)
	 */
	public static <T extends Collection<?>> T allNotNull(T collection, String collectionName)
	{
		if (collectionName == null)
			throw new NullPointerException(
				"collectionName cannot be null. This is about the name of the collection, not the collection itself!");
		if (collection == null)
			throw new NullPointerException("Collection \"" + collectionName + "\" cannot be null");
		for (Object o : collection)
			if (o == null)
				throw new NullPointerException("No element in collection \"" + collectionName + "\" may be null");
		return collection;
	}

	/**
	 * Asserts that the given {@code Collection} is not null, not empty, and that none of its elements are null.
	 * Returns the given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested.
	 * It will be included in the exception message for debugging purposes.
	 * 
	 * @see #copyIfNotEmptyAllNotNull(List, String)
	 * @see #copyIfNotEmptyAllNotNull(Set, String)
	 */
	public static <T extends Collection<?>> T notEmptyAllNotNull(T collection, String collectionName)
	{
		if (collectionName == null)
			throw new NullPointerException(
				"collectionName cannot be null. This is about the name of the collection, not the collection itself!");
		if (collection == null)
			throw new NullPointerException("Collection \"" + collectionName + "\" cannot be null");
		if (collection.isEmpty())
			throw new IllegalStateException("Collection \"" + collectionName + "\" cannot be empty");
		for (Object o : collection)
			if (o == null)
				throw new NullPointerException("No element in collection \"" + collectionName + "\" may be null");
		return collection;
	}

	/**
	 * Asserts that the given {@code Collection<String>} is not null, not empty,
	 * and that none of its elements are null or empty. Returns the given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested.
	 * It will be included in the exception message for debugging purposes.
	 * 
	 * @see #copyIfNotEmptyAllNotEmpty(List, String)
	 * @see #copyIfNotEmptyAllNotEmpty(Set, String)
	 */
	public static <T extends Collection<? extends String>> T notEmptyAllNotEmpty(T collection, String collectionName)
	{
		if (collectionName == null)
			throw new NullPointerException(
				"collectionName cannot be null. This is about the name of the collection, not the collection itself!");
		if (collection == null)
			throw new NullPointerException("Collection \"" + collectionName + "\" cannot be null");
		if (collection.isEmpty())
			throw new IllegalStateException("Collection \"" + collectionName + "\" cannot be empty");
		for (String s : collection)
		{
			if (s == null)
				throw new NullPointerException("No string in collection \"" + collectionName + "\" may be null");
			if (s.isEmpty())
				throw new IllegalStateException("No string in collection \"" + collectionName + "\" may be empty");
		}
		return collection;
	}

	/**
	 * Asserts that the given {@code List} is not null and that none of its elements are null.
	 * Returns a <b>copy</b> of the given list as an {@code ArrayList} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #allNotNull(Collection, String)},
	 * so that looping over the list happens only once.
	 * <p>
	 * {@code listName} should be the name of the list being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #allNotNull(Collection, String)
	 * @see #copyIfAllNotNull(Set, String)
	 */
	public static <T> ArrayList<T> copyIfAllNotNull(List<? extends T> list, String listName)
	{
		if (listName == null)
			throw new NullPointerException("listName cannot be null. This is about the name of the list, not the list itself!");
		if (list == null)
			throw new NullPointerException("List \"" + listName + "\" cannot be null");
		ArrayList<T> copy = new ArrayList<>(list.size());
		for (T t : list)
		{
			if (t == null)
				throw new NullPointerException("No element in list \"" + listName + "\" may be null");
			copy.add(t);
		}
		return copy;
	}

	/**
	 * Asserts that the given {@code Set} is not null and that none of its elements are null.
	 * Returns a <b>copy</b> of the given set as a {@code HashSet} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #allNotNull(Collection, String)},
	 * so that looping over the set happens only once.
	 * <p>
	 * {@code setName} should be the name of the set being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #allNotNull(Collection, String)
	 * @see #copyIfAllNotNull(List, String)
	 */
	public static <T> HashSet<T> copyIfAllNotNull(Set<? extends T> set, String setName)
	{
		if (setName == null)
			throw new NullPointerException("setName cannot be null. This is about the name of the set, not the set itself!");
		if (set == null)
			throw new NullPointerException("Set \"" + setName + "\" cannot be null");
		HashSet<T> copy = new HashSet<>(set.size());
		for (T t : set)
		{
			if (t == null)
				throw new NullPointerException("No element in set \"" + setName + "\" may be null");
			copy.add(t);
		}
		return copy;
	}

	/**
	 * Asserts that the given {@code List} is not null, not empty, and that none of its elements are null.
	 * Returns a <b>copy</b> of the given list as an {@code ArrayList} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #notEmptyAllNotNull(Collection, String)},
	 * so that looping over the list happens only once.
	 * <p>
	 * {@code listName} should be the name of the list being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #notEmptyAllNotNull(Collection, String)
	 * @see #copyIfNotEmptyAllNotNull(Set, String)
	 */
	public static <T> ArrayList<T> copyIfNotEmptyAllNotNull(List<? extends T> list, String listName)
	{
		if (listName == null)
			throw new NullPointerException("listName cannot be null. This is about the name of the list, not the list itself!");
		if (list == null)
			throw new NullPointerException("List \"" + listName + "\" cannot be null");
		if (list.isEmpty())
			throw new IllegalStateException("List \"" + listName + "\" cannot be empty");
		ArrayList<T> copy = new ArrayList<>(list.size());
		for (T t : list)
		{
			if (t == null)
				throw new NullPointerException("No element in list \"" + listName + "\" may be null");
			copy.add(t);
		}
		return copy;
	}

	/**
	 * Asserts that the given {@code Set} is not null, not empty, and that none of its elements are null.
	 * Returns a <b>copy</b> of the given set as a {@code HashSet} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #notEmptyAllNotNull(Collection, String)},
	 * so that looping over the set happens only once.
	 * <p>
	 * {@code setName} should be the name of the set being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #notEmptyAllNotNull(Collection, String)
	 * @see #copyIfNotEmptyAllNotNull(List, String)
	 */
	public static <T> HashSet<T> copyIfNotEmptyAllNotNull(Set<? extends T> set, String setName)
	{
		if (setName == null)
			throw new NullPointerException("setName cannot be null. This is about the name of the set, not the set itself!");
		if (set == null)
			throw new NullPointerException("Set \"" + setName + "\" cannot be null");
		if (set.isEmpty())
			throw new IllegalStateException("Set \"" + setName + "\" cannot be empty");
		HashSet<T> copy = new HashSet<>(set.size());
		for (T t : set)
		{
			if (t == null)
				throw new NullPointerException("No element in set \"" + setName + "\" may be null");
			copy.add(t);
		}
		return copy;
	}

	/**
	 * Asserts that the given {@code List} of Strings is not null, not empty,
	 * and that none of its elements are null or empty. Returns a <b>copy</b>
	 * of the given list as an {@code ArrayList} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #notEmptyAllNotEmpty(Collection, String)},
	 * so that looping over the list happens only once.
	 * <p>
	 * {@code listName} should be the name of the list being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #notEmptyAllNotEmpty(Collection, String)
	 * @see #copyIfNotEmptyAllNotEmpty(Set, String)
	 */
	public static ArrayList<String> copyIfNotEmptyAllNotEmpty(List<String> list, String listName)
	{
		if (listName == null)
			throw new NullPointerException("listName cannot be null. This is about the name of the list, not the list itself!");
		if (list == null)
			throw new NullPointerException("List \"" + listName + "\" cannot be null");
		if (list.isEmpty())
			throw new IllegalStateException("List \"" + listName + "\" cannot be empty");
		ArrayList<String> copy = new ArrayList<>(list.size());
		for (String s : list)
		{
			if (s == null)
				throw new NullPointerException("No element in list \"" + listName + "\" may be null");
			if (s.isEmpty())
				throw new IllegalStateException("No string in list \"" + listName + "\" may be empty");
			copy.add(s);
		}
		return copy;
	}

	/**
	 * Asserts that the given {@code Set} of Strings is not null, not empty,
	 * and that none of its elements are null or empty. Returns a <b>copy</b>
	 * of the given set as a {@code HashSet} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is better to use this method than {@link #notEmptyAllNotEmpty(Collection, String)},
	 * so that looping over the set happens only once.
	 * <p>
	 * {@code setName} should be the name of the set being tested. It will be included in the exception message for debugging purposes.
	 * 
	 * @see #notEmptyAllNotEmpty(Collection, String)
	 * @see #copyIfNotEmptyAllNotEmpty(List, String)
	 */
	public static HashSet<String> copyIfNotEmptyAllNotEmpty(Set<String> set, String setName)
	{
		if (setName == null)
			throw new NullPointerException("setName cannot be null. This is about the name of the set, not the set itself!");
		if (set == null)
			throw new NullPointerException("Set \"" + setName + "\" cannot be null");
		if (set.isEmpty())
			throw new IllegalStateException("Set \"" + setName + "\" cannot be empty");
		HashSet<String> copy = new HashSet<>(set.size());
		for (String s : set)
		{
			if (s == null)
				throw new NullPointerException("No element in set \"" + setName + "\" may be null");
			if (s.isEmpty())
				throw new IllegalStateException("No string in set \"" + setName + "\" may be empty");
			copy.add(s);
		}
		return copy;
	}
}