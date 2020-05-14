package com.laundry.ordermgmt.repository;

import org.springframework.data.repository.CrudRepository;

import com.laundry.ordermgmt.repository.entity.Sequence;

public interface SequenceRespository extends CrudRepository<Sequence, String>
{

}