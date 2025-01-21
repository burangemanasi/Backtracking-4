//1087. https://leetcode.com/problems/brace-expansion/description/
//Time Complexity: O(k ^ n)
//              where k: avg size of each grp
//              n: level of tree
//              OR
//              O(k ^ (N/k)), here N: length of the 's'
//Space Complexity: O(n), recursive Stack Space

class Solution {
    List<String> result;
    public String[] expand(String s) {
        this.result = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        int i = 0; //index to iterate over 's'
        //create groups
        //go over all the characters of "s"
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> grp = new ArrayList<>();
            if(c == '{'){
                i++; //skip opening brace
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        grp.add(s.charAt(i));
                    }
                    i++;
                }
                i++; //skip closing braces
            } else {
                grp.add(s.charAt(i)); //no { or }, just a character, add to grp
                i++;
            }
            //sort before adding to the groups
            Collections.sort(grp);
            groups.add(grp); //[[a,b],[c],[l],[m],[d,e],[f]]
        }
        dfs(groups, 0, new StringBuilder());

        String[] arrResult = new String[result.size()];
        for(int k=0; k<result.size(); k++){
            arrResult[k] = result.get(k);
        }
        return arrResult;

    }

    private void dfs(List<List<Character>> groups, int idx, StringBuilder path){
        //base case
        if(idx == groups.size()){
            result.add(path.toString());
            return;
        }
        //logic
        List<Character> currGroup = groups.get(idx);
        for(int i=0; i<currGroup.size(); i++){
            int pathLen = path.length();
            //action
            path.append(currGroup.get(i));
            //recurse
            dfs(groups, idx+1, path);
            //backtrack
            path.setLength(pathLen);
        }
    }
}