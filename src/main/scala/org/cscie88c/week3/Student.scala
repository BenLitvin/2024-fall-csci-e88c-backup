package org.cscie88c.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  // Validating email. Checks if the email contains "@"
  def validateEmail(student: Student): Boolean = {
    student.email.contains("@")
  }

  // Calculating average score by subject
  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
  ): Double = {
    val subjectStudents = studentList.filter(_.subject == subject)
    if (subjectStudents.isEmpty) 0.0
    else subjectStudents.map(_.score).sum.toDouble / subjectStudents.size
  }

  // Calculating average score by student
  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
  ): Double = {
    val studentScores = studentList.filter(_.name == student.name)
    if (studentScores.isEmpty) 0.0
    else studentScores.map(_.score).sum.toDouble / studentScores.size
  }
}
