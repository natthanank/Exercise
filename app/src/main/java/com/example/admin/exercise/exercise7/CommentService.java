package com.example.admin.exercise.exercise7;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentService {

    @GET("comments")
    Call<List<Comment>> listComment(@Query("postId") int postId);
}
