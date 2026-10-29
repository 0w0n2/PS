class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] ans = new int[arr1.length][arr2[0].length]; // 행렬 곱셈 성질: [axb] x [bxc] = [axc]

        for (int i=0; i<arr1.length; i++) {
            for (int a=0; a<arr2[0].length; a++) {
                for (int k=0; k<arr1[0].length; k++) {
                    ans[i][a] += arr1[i][k] * arr2[k][a];
                }
            }
        }

        return ans;
    }
}