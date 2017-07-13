/**
 * The superhero entity.
 */
package net.luisalbertogh.springbatch.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

/**
 * @author lagarcia
 *
 */
@Entity
public final class Superhero {
	/** The name */
	private String name;

	/** The super power */
	private String power;

	/** The evil actitude */
	private Boolean isEvil;

	/** The creation date and time */
	private LocalDateTime createdDate;

	/** The creator name */
	private String creator;

	/** The superhero weakness */
	private String weakness;

	/**
	 * Default constructor.
	 */
	public Superhero() {
		/* Empty */
	}

	/**
	 * Constructor with args.
	 *
	 * @param nameArg
	 * @param powerArg
	 * @param isEvilArg
	 */
	public Superhero(String nameArg, String powerArg, Boolean isEvilArg) {
		name = nameArg;
		power = powerArg;
		isEvil = isEvilArg;
	}

	/**
	 * @return the name
	 */
	@Id
	public String getName() {
		return name;
	}

	/**
	 * @param nameArg
	 *            the name to set
	 */
	public void setName(String nameArg) {
		name = nameArg;
	}

	/**
	 * @return the power
	 */
	public String getPower() {
		return power;
	}

	/**
	 * @param powerArg
	 *            the power to set
	 */
	public void setPower(String powerArg) {
		power = powerArg;
	}

	/**
	 * @return the isEvil
	 */
	public Boolean getIsEvil() {
		return isEvil;
	}

	/**
	 * @param isEvilArg
	 *            the isEvil to set
	 */
	public void setIsEvil(Boolean isEvilArg) {
		isEvil = isEvilArg;
	}

	/**
	 * @return the createdDate
	 */
	@CreatedDate
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDateArg
	 *            the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDateArg) {
		createdDate = createdDateArg;
	}

	/**
	 * @return the creator
	 */
	@CreatedBy
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creatorArg
	 *            the creator to set
	 */
	public void setCreator(String creatorArg) {
		creator = creatorArg;
	}

	/**
	 * @return the weakness
	 */
	public String getWeakness() {
		return weakness;
	}

	/**
	 * @param weaknessArg
	 *            the weakness to set
	 */
	public void setWeakness(String weaknessArg) {
		weakness = weaknessArg;
	}

	@Override
	public String toString() {
		/* Evil */
		String evilStr = "good";
		if (isEvil) {
			evilStr = "evil";
		}

		/* Creation date */
		String dateStr = "";
		if (createdDate != null) {
			dateStr = createdDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		}

		return "[" + dateStr + "][" + creator + "] My name is <b>" + name
				+ "</b> and my super power is <b>" + power
				+ "</b>. Do not panic, I am " + evilStr + ". I am afraid to "
				+ weakness;
	}
}
