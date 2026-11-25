using System;
using System.IO;

class Program {
    public static void Main(string[] args){
        var tokens = Console.In.ReadToEnd().Split((char[]) null, StringSplitOptions.RemoveEmptyEntries);

        int n = int.Parse(tokens[0]);
        int cur = int.Parse(tokens[1]);
        int ans = cur;

        for (int i=1; i<n; i++) {
            int next = int.Parse(tokens[i+1]);
            cur = Math.Max(next, cur + next);
            ans = Math.Max(ans, cur);
        }

        Console.WriteLine(ans);
    }

}