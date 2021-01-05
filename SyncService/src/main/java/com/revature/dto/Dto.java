  
package com.revature.dto;

/**
 * A data transfer object, designed to facilitate the transformation 
 * between external and internal data in a Java program. This functional
 * interface contains a method that converts itself into its Pojo equivalent.
 * Generic type should be the Pojo this Dto relates to.
 * @author Chris,
 * @author Conner,
 * @author Michael M,
 * @author Michael Z,
 * @author Prativa,
 * @author Vincent
 */
public interface Dto<T> {
	
	/**
	 * Converts this Dto object into its Pojo equivalent.
	 * @return Java Pojo
	 */
	public T toPojo();

}