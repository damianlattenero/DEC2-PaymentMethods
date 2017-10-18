package mvctest.service

import org.springframework.data.repository.CrudRepository
import mvctest.domain.PaymentMethods
import java.lang.Long

trait PaymentMethodRepository extends CrudRepository[PaymentMethods, Long]