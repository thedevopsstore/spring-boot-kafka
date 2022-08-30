package db.demo.back.repository

import db.demo.back.model.TestEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : CrudRepository<TestEntity, Long>