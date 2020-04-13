object p3_string {
  def string_ops(): Unit = {
    var str_constr = new String()

    var str1 = "abc"
    var str2 = "def"
    var str3 = "ghi"

    println(str1.charAt(1))
    println(str1.compareTo(str2))
    println(str1.concat(str2))
    println(str1.contains(str2))
    println(str1.compareToIgnoreCase(str2))
    println(str1.endsWith(str2))
    println(str1.equals(str2))
    println(str1.getBytes())
    println(str1.hashCode())
    println(str1.indexOf('a'))
    println(str1.isEmpty())
    println(str1.lastIndexOf('a'))
    println(str1.length())
    println(str1.replace('a', 'c'))
    println(str1.substring(2))
    println(str1.toUpperCase())
    println(str1.toLowerCase())
    println(str1.trim())
  }
}
