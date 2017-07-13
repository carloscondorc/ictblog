/**
 * Spring JPA auditor aware implementation
 */
package net.luisalbertogh.springbatch.audit;

import org.springframework.data.domain.AuditorAware;

/**
 * @author lagarcia
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {
	/**
	 * Current auditor.
	 */
	@Override
	public String getCurrentAuditor() {
		return "Dr. Maligno";
	}
}
