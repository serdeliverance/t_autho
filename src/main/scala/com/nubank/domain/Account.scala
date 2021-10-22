package com.nubank.domain

case class Account(activeCard: Boolean, availableLimit: Int, transactions: List[Transaction])
