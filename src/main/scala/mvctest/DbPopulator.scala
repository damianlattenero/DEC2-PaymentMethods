package mvctest

import mvctest.domain.PaymentMethods
import mvctest.service.PaymentMethodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
  * Responsible for populating some initial data into the database..
  */

@Component
class DbPopulator @Autowired()(val hotelRepository: PaymentMethodRepository) extends CommandLineRunner {
  override def run(args: String*): Unit = {
    (1 to 10).foreach(i => {
      hotelRepository.save(new PaymentMethods(id=null, name = s"Payment Method $i", address = s"Name $i", zip = s"Zip $i"))
    })
  }
}
