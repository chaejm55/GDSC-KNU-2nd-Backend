package jpabook.jpashop.exception

import java.io.PrintStream
import java.io.PrintWriter

class NotEnoughStockException(message: String): Exception(message)