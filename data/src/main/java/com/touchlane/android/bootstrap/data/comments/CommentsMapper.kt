package com.touchlane.android.bootstrap.data.comments

import com.touchlane.android.bootstrap.data.Mapper
import com.touchlane.android.bootstrap.domain.comments.Comment

class CommentsMapper : Mapper<List<CommentDto>, List<Comment>> {

    override fun map(input: List<CommentDto>): List<Comment> = input.map { it.toComment() }
}