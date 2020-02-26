package com.trampcr.developerrepository.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeTraverse {
    private List<Integer> preList = new ArrayList<>();
    private List<Integer> preList1 = new ArrayList<>();
    private List<Integer> midList = new ArrayList<>();
    private List<Integer> midList1 = new ArrayList<>();
    private List<Integer> postList = new ArrayList<>();
    private List<Integer> postList1 = new ArrayList<>();
    public List<Integer> preOrder(TreeNode node) {
        if (node == null) {
            return null;
        }

        preList.add(node.val);

        if (node.left != null) {
            preOrder(node.left);
        }

        if (node.right != null) {
            preOrder(node.right);
        }

        return preList;
    }

    public List<Integer> preOrder1(TreeNode node) {
        if (node == null) {
            return preList1;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            preList1.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }

        return preList1;
    }

    public List<Integer> midOrder(TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            midOrder(node.left);
        }

        midList.add(node.val);

        if (node.right != null) {
            midOrder(node.right);
        }

        return midList;
    }

    public List<Integer> midOrder1(TreeNode node) {
        if (node == null) {
            return midList1;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            curr = stack.pop();
            midList1.add(curr.val);
            node = curr.right;
        }

        return midList1;
    }

    public List<Integer> postOrder(TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            postOrder(node.left);
        }

        if (node.right != null) {
            postOrder(node.right);
        }

        postList.add(node.val);

        return postList;
    }

    public List<Integer> postOrder1(TreeNode node) {
        if (node == null) {
            return postList1;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            postList1.add(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (curr.right != null) {
                stack.push(curr.right);
            }
        }

        Collections.reverse(postList1);
        return postList1;
    }

    public List<Integer> layerTraverse(TreeNode node) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        queue.offer(node);
        TreeNode curr;

        while (!queue.isEmpty()) {
            curr = queue.poll();
            result.add(curr.val);

            if (curr.left != null) {
                queue.offer(curr.left);
            }

            if (curr.right != null) {
                queue.offer(curr.right);
            }
        }

        return result;
    }
}
