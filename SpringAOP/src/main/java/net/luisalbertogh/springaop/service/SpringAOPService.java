/**
 * A simple service.
 */
package net.luisalbertogh.springaop.service;

import org.springframework.stereotype.Service;

/**
 * @author lagarcia
 *
 */
@Service
public class SpringAOPService {
	/**
	 * Count the number of 'c' appearances in the passed word.
	 *
	 * @param word
	 *            - The word
	 * @param c
	 *            - The char to count
	 * @return The number of times the c char is in the word
	 */
	public Integer countCharsService(String word, char c) {
		int count = 0;
		char[] chars = word.toCharArray();
		for (char ch : chars) {
			if (ch == c) {
				count++;
			}
		}

		return new Integer(count);
	}
}
