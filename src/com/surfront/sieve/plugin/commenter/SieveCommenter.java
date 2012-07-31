package com.surfront.sieve.plugin.commenter;

import com.intellij.lang.Commenter;

public class SieveCommenter implements Commenter {
    public String getLineCommentPrefix() {
        return "#";
    }

    public String getBlockCommentPrefix() {
        return "/*";
    }

    public String getBlockCommentSuffix() {
        return "*/";
    }

    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
