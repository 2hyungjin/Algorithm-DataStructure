package tree
/*
    1
   2 3
  4 5
 */
fun main() {
    val tree=BinaryTree<Int>()
    val n4=tree.makeNode(null,4,null)
    val n5=tree.makeNode(null,5,null)
    val n2=tree.makeNode(n4,2,n5)
    val n3=tree.makeNode(null,3,null)
    val n1=tree.makeNode(n2,1,n3)
    tree.root=n1

    tree.postOrder(tree.root)
}

data class BinaryTree<T>(
    var root: Node<T>? = null,
) {
    fun makeNode(left: Node<T>?, data: T, right: Node<T>?) : Node<T>{
        val node = Node<T>()
        node.data = data
        node.left = left
        node.right = right
        return node
    }

    fun inOrder(node: Node<T>?) {
        node?.run {
            inOrder(left)
            println(data)
            inOrder(right)
        }
    }

    fun preOrder(node: Node<T>?) {
        node?.run {
            println(data)
            preOrder(left)
            preOrder(right)
        }
    }

    fun postOrder(node: Node<T>?) {
        node?.run {
            postOrder(left)
            postOrder(right)
            println(data)
        }
    }
}

data class Node<T>(
    var data: T? = null,
    var left: Node<T>? = null,
    var right: Node<T>? = null,
)