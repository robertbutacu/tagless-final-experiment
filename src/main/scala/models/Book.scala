package models

import models.types.{Author, Description, Title, Year}

case class Book(title: Title, author: Author, year: Year, description: Description)
