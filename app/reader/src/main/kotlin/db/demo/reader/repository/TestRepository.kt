package db.demo.reader.repository

import db.demo.reader.model.TestEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : PagingAndSortingRepository<TestEntity, Long>