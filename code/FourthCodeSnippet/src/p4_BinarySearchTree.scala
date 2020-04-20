object p4_BinarySearchTree{
  class BSTNode(var element: Int, var left: BSTNode, var right: BSTNode){
    def value(): Int = {
      return this.element
    }

    def setValue(v: Int): Unit = {
      this.element = v
    }

    def getLeft(): BSTNode = {
      return this.left
    }

    def setLeft(p: BSTNode): Unit = {
      this.left = p
    }

    def getRight(): BSTNode = {
      return this.right
    }

    def setRight(p: BSTNode): Unit = {
      this.right = p
    }

    def isLeaf(): Boolean = {
      return ((this.left == null) && (this.right == null))
    }
  }

  class p4_BinarySearchTree(var root: BSTNode, var nodeCount: Int) {
    def clear(): Unit = {
      root = null
      nodeCount = 0
    }

    def insert(e: Int): Unit = {
      root = inserthelp(root, e)
    }

    def inserthelp(rt: BSTNode, e: Int): BSTNode = {
      if(rt == null){
        return new BSTNode(e, null, null)
      }
      if(rt.value() >= e){
        rt.setLeft(inserthelp(rt.getLeft(), e))
      }
      else{
        rt.setRight(inserthelp(rt.getRight(), e))
      }
      return rt
    }

    def remove(key: Int): Int = {
      var temp = findhelp(root, key)
      if(temp != null){
        root = removehelp(root, key)
        nodeCount -= 1
      }
      return temp
    }

    def removehelp(rt: BSTNode, key: Int): BSTNode = {
      if(rt == null){
        return null
      }
      if(rt.value() > key){
        rt.setLeft(removehelp(rt.left(), key))
      }
      else if(rt.value() < key){
        rt.setRight(removehelp(rt.right(), key))
      }
      else{
        if(rt.getLeft() == null){
          return rt.getRight()
        }
        else if(rt.getRight() == null){
          return rt.getLeft()
        }
        else{
          var temp = getmax(rt.getLeft())
          rt.setValue(temp.value())
          rt.setLeft(deletemax(rt.left()))
        }
      }
      return rt
    }

    def deletemax(rt: BSTNode): BSTNode = {
      if(rt.getRight() == null){
        return rt.getLeft()
      }
      rt.setRight(deletemax(rt.getRight()))
      return rt
    }

    def getmax(rt: BSTNode): BSTNode = {
      if(rt.getRight() == null){
        return rt
      }
      return getmax(rt.right())
    }

    def find(key: Int): Int = {
      return findhelp(root, key)
    }

    def findhelp(rt: BSTNode, key: Int): Int = {
      if(rt == null){
        return null;
      }
      if(rt.value() > key){
        return findhelp(rt.left(), key)
      }
      else if(rt.value() < key){
        return findhelp(rt.right(), key)
      }
      else{
        return rt.value()
      }
    }
  }
}
