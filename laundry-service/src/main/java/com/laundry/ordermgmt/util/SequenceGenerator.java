package com.laundry.ordermgmt.util;

import javax.validation.ValidationException;

import com.laundry.ordermgmt.constant.ApplicationMessages;
import com.laundry.ordermgmt.repository.SequenceRespository;
import com.laundry.ordermgmt.repository.entity.Sequence;

/**
 * The Class SequenceGenerator.
 */
public class SequenceGenerator {

	/**
	 * Gets the primary key.
	 * 
	 * @param clazz the clazz
	 * @return the primary key
	 */
	public static Long getPrimaryKey(Class<?> clazz) {
		SequenceRespository sequenceRepo = ApplicationContextUtil.getBean(SequenceRespository.class);
		Sequence sequence = sequenceRepo.findById(clazz.getSimpleName()).orElseThrow(() -> new ValidationException(
				String.format(ApplicationMessages.FIELD_DOES_NOT_EXIST_IN_DB, "Sequence")));

		// Return Sequence Id
		Long sequenceId = sequence.getSequenceId();
		sequence.setSequenceId(sequenceId + 1);
		sequenceRepo.save(sequence);
		return sequenceId;
	}
}
