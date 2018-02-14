package com.party.plan.repositories;

import org.springframework.data.repository.CrudRepository;

import com.party.plan.models.db.Members;

public interface MembersRepository extends CrudRepository<Members, String> {

}
