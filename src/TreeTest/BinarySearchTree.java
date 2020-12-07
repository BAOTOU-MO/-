package TreeTest;

import com.sun.corba.se.impl.orbutil.graph.NodeData;

import javax.xml.soap.Node;
import java.nio.BufferUnderflowException;

//该方式表示参数类型T是一个实现了Comparable<S>,S是T的父类
public class BinarySearchTree<T extends Comparable<? super T>>{
    private static class BinaryNode<T>{
        T NodeData;
        BinaryNode<T> leftNode;
        BinaryNode<T> rightNode;

        BinaryNode(T NodeData){
            this(NodeData,null,null);
        }

        BinaryNode(T NodeData,BinaryNode<T> leftNode,BinaryNode<T> rightNode){
            this.NodeData= NodeData;
            this.leftNode=leftNode;
            this.rightNode=rightNode;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree(){
        root=null;
    }

    public void makeEmpty(){
        root=null;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public boolean contains(T data){
        return contains(data,root);
    }

    public T findMin(){
        if(isEmpty()) throw new BufferUnderflowException();
        return findMin(root).NodeData;
    }

    public T findMax(){
        if(isEmpty()) throw new BufferUnderflowException();
        return findMax(root).NodeData;
    }

    public void insert(T data){
        root=insert(data,root);
    }

    public void remove(T data){
        root=remove(data,root);
    }

    public void printTree(){

    }


    //判断以该节点开始的二叉查找树是否有当前数据，有则返回0
    private boolean contains(T data,BinaryNode<T> tree){
        if(tree ==null){
            return false;
        }
        int compareResult=data.compareTo(tree.NodeData);

        if(compareResult<0){
            return contains(data,tree.leftNode);
        }else if(compareResult>0){
            return contains(data,tree.rightNode);
        }else {
            return true;
        }

    }

    private BinaryNode<T> findMin(BinaryNode<T> tree){
        if(tree!=null){
            return null;
        }else if(tree.leftNode==null){
            return tree;
        }
        return findMin(tree.leftNode);
    }

    private BinaryNode<T> findMax(BinaryNode<T> tree){
        while(tree!= null){
            while(tree.rightNode!=null){
                tree=tree.rightNode;
            }
        }
        return tree;
    }

    private BinaryNode<T> insert(T data,BinaryNode<T> tree){
        if(tree==null){
            return new BinaryNode<>(data,null,null);
        }

        int compareResult= data.compareTo(tree.NodeData);

        if(compareResult<0){
            tree.leftNode=insert(data,tree.leftNode);
        }else if(compareResult >0){
            tree.rightNode=insert(data,tree.rightNode);
        }else
            ;
        return tree;
    }

    private  BinaryNode<T> remove(T data,BinaryNode<T> tree){
        if(tree==null){
            return  tree;
        }

        int compareResult=data.compareTo(tree.NodeData);

        if(compareResult<0){
            tree.leftNode=remove(data,tree.leftNode);
        }else if(compareResult>0){
            tree.rightNode=remove(data,tree.rightNode);
        }else if(tree.leftNode!=null&&tree.rightNode!=null){
            tree.NodeData=findMin(tree.rightNode).NodeData;
            tree.rightNode=remove(tree.NodeData,tree.rightNode);
        }
        else
            tree=(tree.leftNode!=null)?tree.leftNode:tree.rightNode;
        return tree;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree=new BinarySearchTree<>();
    }
}
//public class BinarySearchTree<T extends Comparable>{
//
//}
//public class BinarySearchTree<T extends Comparable<T>>{
//
//}
//public class BinarySearchTree{
//    public static <T extends Comparable<? super T>> T findMax(T[] arr){
//        int maxIndex=0;
//
//        return arr[maxIndex];
//    }
//
//    public static void main(String[] args) {
//
//    }
//}