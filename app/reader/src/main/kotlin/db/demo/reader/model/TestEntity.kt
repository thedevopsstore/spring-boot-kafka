package db.demo.reader.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class TestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long? = null

  var message: String? = null

  var calculated: Double? = null
}