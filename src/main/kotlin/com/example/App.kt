package com.example

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.runtime.Micronaut.run
import jakarta.inject.Singleton
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

fun main(args: Array<String>) {
	run(*args)
}

@Entity
@Table(name = "person")
class Person (
	@Column(name = "name")
	val name: String
) {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

	override fun toString(): String = "Person(name='$name', id=$id)"

}

@Repository
interface PersonRepository: JpaRepository<Person, Long>

@Singleton
class StartupListener(private val personRepository: PersonRepository): ApplicationEventListener<StartupEvent> {

	override fun onApplicationEvent(event: StartupEvent?) {
		personRepository.saveAll(
			listOf(Person("Foo"), Person("Bar"), Person("Baz"))
		)

		personRepository.findAll()
			.forEach { println(it) }
	}
}
