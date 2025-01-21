//Try to place the building in H*W matrix such that the distance of the farthest parking spot is minimum
//Time Complexity: 2^(m*n)

//h: height of matrix, w: width of matrix, n: no. of buildings
public static class BuildingPlacement{
    int H, W;
    int result;

    public int findMinDistance(int h, int w, int n){
        this.H = h;
        this.W = w;
        this.result = Integer.MAX_VALUE;

        int[][] grid = new int[H][W];
        //initialize the grid
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                //all parking spots are -1
                grid[i][j] = -1;
            }
        }

        dfs(grid, 0, 0, n);

        return result;

    }

    private void dfs(int[][] grid, int i, int j, int n){
        //base case
        if(n == 0){
            bfs(grid);
            return;
        }
        //check bounds of the grid
        if(j == W){
            j=0; //j: 0th col
            i++; //next row
        }

        //logic
        for(int r=i; r<H; r++){
            for(int c=j; c<W; c++){
                //place the building at initial location
                //action
                grid[r][c]=0;
                //row is same, keep moving c to each col
                //n-1: remaining bilding to place
                //recurse
                dfs(grid, r, c+1, n-1);
                //backtrack
                grid[r][c] = -1;
            }
        }
    }

    private void bfs(int[][] grid) {
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited = new boolean[H][W];
        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++) {
                if(grid[i][j] == 0){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        int dist = 0; //initial distance from building
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0; k<size; k++){
                int[] curr = q.poll();
                for(int[] dir: dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];

                    if(r>=0 && c>=0 && r<H && c<W && !visited[r][c] && grid[r][c] == -1){
                        q.add(new int[]{r,c});
                        visited[r][c] = true;
                    }
                }
            }
            dist++;
        }
        result = Math.min(result, dist-1);
    }
}