package com.zieglersoftware.assertions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class Assertions
{
	private Assertions()
	{
	}

	/**
	 * Asserts that the given {@code Object} is not {@code null}. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code boolean} is {@code true}.
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
	 * Asserts that the given {@code boolean} is {@code false}.
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
	 * {@code null}, the assertions passes. If one is {@code null} but the other is not, the assertion fails. If both are non-{@code null},
	 * {@code object.equals(referenceValue)} is used to make the comparison. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the object being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static <T> T equal(T object, Object referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (object == null)
		{
			if (referenceValue == null)
				return object;
			else
				throw new IllegalStateException(
					"Variable \"" + variableName + "\" must be equal to " + referenceValue + ", but was null instead");
		}
		else if (!object.equals(referenceValue))
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be equal to " + referenceValue + ". Was " + object);
		return object;
	}

	/**
	 * Asserts that the given object is not equal to the given reference value. If both the given object and the reference value are
	 * {@code null}, the assertions fails. If one is {@code null} but the other is not, the assertion passes. If both are non-{@code null},
	 * {@code object.equals(referenceValue)} is used to make the comparison. Returns the given object if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the object being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static <T> T notEqual(T object, Object referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (object == null)
		{
			if (referenceValue == null)
				throw new IllegalStateException(
					"Variable \"" + variableName + "\" must not be equal to the given reference value, but both are null");
			else
				return object;
		}
		else if (object.equals(referenceValue))
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be equal to " + referenceValue);
		return object;
	}

	/**
	 * Asserts that the given {@code String} is not {@code null} and not empty. Returns the given string if the assertion succeeds.
	 * <p>
	 * {@code stringName} should be the name of the string being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code Optional} is not {@code null} and is {@code present}. Returns the present object, i.e.,
	 * {@code optionalObject.get()}} if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code long} is equal to the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long equal(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng != referenceValue)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be equal to " + referenceValue + ". Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is not equal to the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long notEqual(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng == referenceValue)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be equal to " + referenceValue);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is greater than the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long greater(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng <= referenceValue)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be greater than " + referenceValue + ". Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is not greater than the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long notGreater(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng > referenceValue)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must not be greater than " + referenceValue + ". Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is less than the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long less(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng >= referenceValue)
			throw new IllegalStateException("Variable \"" + variableName + "\" must be less than " + referenceValue + ". Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is not less than the given reference value. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long notLess(long lng, long referenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng < referenceValue)
			throw new IllegalStateException("Variable \"" + variableName + "\" must not be less than " + referenceValue + ". Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code long} is between the two given reference values, inclusive, i.e.,
	 * {@code lowReferenceValue <= lng <= highReferenceValue}. Returns the given long if the assertion succeeds.
	 * <p>
	 * {@code variableName} should be the name of the variable being tested. It will be included in the exception message for debugging
	 * purposes.
	 */
	public static long between(long lng, long lowReferenceValue, long highReferenceValue, String variableName)
	{
		if (variableName == null)
			throw new NullPointerException("variableName cannot be null. This is about the variable name, not the variable itself!");
		if (lng < lowReferenceValue || lng > highReferenceValue)
			throw new IllegalStateException(
				"Variable \"" + variableName + "\" must be between " +
					lowReferenceValue + " and " + highReferenceValue + ", inclusive. Was " + lng);
		return lng;
	}

	/**
	 * Asserts that the given {@code Collection} is not {@code null} and not empty. Returns the given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code Collection} is not {@code null} and that none of its elements are {@code null}. Returns the given
	 * collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code List} is not {@code null} and that none of its elements are {@code null}. Returns a <b>copy</b> of the
	 * given list as an {@code ArrayList} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is likely more efficient to use this method than {@link #allNotNull(Collection, String)}, so that looping
	 * over the list happens only once.
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
	 * Asserts that the given {@code Set} is not {@code null} and that none of its elements are {@code null}. Returns a <b>copy</b> of the
	 * given set as a {@code HashSet} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is likely more efficient to use this method than {@link #allNotNull(Collection, String)}, so that looping
	 * over the set happens only once.
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
	 * Asserts that the given {@code Collection} is not {@code null}, not empty, and that none of its elements are {@code null}. Returns the
	 * given collection if the assertion succeeds.
	 * <p>
	 * {@code collectionName} should be the name of the collection being tested. It will be included in the exception message for debugging
	 * purposes.
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
	 * Asserts that the given {@code List} is not {@code null}, not empty, and that none of its elements are {@code null}. Returns a
	 * <b>copy</b> of the given list as an {@code ArrayList} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is likely more efficient to use this method than {@link #notEmptyAllNotNull(Collection, String)}, so that
	 * looping over the list happens only once.
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
	 * Asserts that the given {@code Set} is not {@code null}, not empty, and that none of its elements are {@code null}. Returns a
	 * <b>copy</b> of the given set as a {@code HashSet} if the assertion succeeds.
	 * <p>
	 * If a copy is required, it is likely more efficient to use this method than {@link #notEmptyAllNotNull(Collection, String)}, so that
	 * looping over the set happens only once.
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
}