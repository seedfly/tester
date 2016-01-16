package froex.simulation;

public class FunctionBinaryTree {
	public TreeNode[] treeNodes;

	public FunctionBinaryTree(byte[] genes) {
		int treeNodesNum = genes.length;
		treeNodes = new TreeNode[treeNodesNum];
		for (int i = 0; i < treeNodesNum; i++) {
			treeNodes[i] = new TreeNode(i, genes[i]);
		}
		for (int i = 0; i < treeNodesNum; i++) {
			if ((2 * i + 1) >= treeNodesNum) {
				break;
			}
			treeNodes[i].leftChild = treeNodes[2 * i + 1];
			treeNodes[i].rightChild = treeNodes[2 * i + 2];
		}
	}

	public double getFunctionRes() {
		return getTreeVal(treeNodes[0]);
	}

	private double getTreeVal(TreeNode treeNode) {
		double leftValue = 0;
		double rightValue = 0;
		if (treeNode.leftChild != null) {
			leftValue = getTreeVal(treeNode.leftChild);
		}
		if (treeNode.rightChild != null) {
			rightValue = getTreeVal(treeNode.rightChild);
		}
		if (treeNode.leftChild == null && treeNode.rightChild == null) {
			return treeNode.data;
		}
		return Func.values()[(int) (Math.abs(treeNode.data) % Func.values().length)].calc(leftValue, rightValue);
	}

	public class TreeNode {
		private int key = 0;
		private double data = 0;
		private TreeNode leftChild = null;
		private TreeNode rightChild = null;

		/**
		 * @param key
		 *            层序编码
		 * @param data
		 *            数据域
		 */
		public TreeNode(int key, double data) {
			this.key = key;
			this.data = data;
			this.leftChild = null;
			this.rightChild = null;
		}

		public double getData() {
			return data;
		}

		public void setData(double data) {
			this.data = data;
		}
	}

	enum Func {
		PLUS {
			@Override
			public double calc(double x, double y) {
				return x + y;
			}
		},
		MINUS {
			@Override
			public double calc(double x, double y) {
				return x - y;
			}
		},
		MUTI {
			@Override
			public double calc(double x, double y) {
				return x * y;
			}
		},
		DIVIDE {
			@Override
			public double calc(double x, double y) {
				return y == 0 ? x : x / y;
			}
		},
		AVG {
			@Override
			public double calc(double x, double y) {
				return (x + y) / 2;
			}
		},
		MAX {
			@Override
			public double calc(double x, double y) {
				return x > y ? x : y;
			}
		},
		X2 {
			@Override
			public double calc(double x, double y) {
				return 2 * (x + y);
			}
		},
		X3 {
			@Override
			public double calc(double x, double y) {
				return 3 * (x + y);
			}
		},
		X4 {
			@Override
			public double calc(double x, double y) {
				return 4 * (x + y);
			}
		},
		D3 {
			@Override
			public double calc(double x, double y) {
				return (x + y) / 3;
			}
		},
		MIN {
			@Override
			public double calc(double x, double y) {
				return x < y ? x : y;
			}
		},;

		abstract public double calc(double x, double y);

	}

	public static void main(String[] args) {
		byte[] g = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		FunctionBinaryTree t = new FunctionBinaryTree(g);
		System.out.println(t.getFunctionRes());
	}
}
