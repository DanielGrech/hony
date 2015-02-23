package com.dgsd.android.hony.domain.db.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dgsd.android.hony.domain.model.HonyComment;
import com.dgsd.android.hony.domain.model.HonyPost;

import java.util.List;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.ModelList;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Sprinkles;
import se.emilsjolander.sprinkles.Transaction;

import static com.dgsd.android.hony.domain.db.backend.DatabaseBackend.Contract.TABLE_COMMENTS;
import static com.dgsd.android.hony.domain.db.backend.DatabaseBackend.Contract.TABLE_POSTS;

public class SprinklesDatabaseBackend implements DatabaseBackend {

    private final Sprinkles sprinkles;

    private static SprinklesDatabaseBackend instance;

    public static SprinklesDatabaseBackend getInstance(Context context) {
        if (instance == null) {
            instance = new SprinklesDatabaseBackend(context.getApplicationContext());
        }
        return instance;
    }

    private SprinklesDatabaseBackend(Context context) {
        sprinkles = Sprinkles.init(context);
        sprinkles.addMigration(new Version1DbMigration());
    }

    @Override
    public void save(List<HonyPost> postList) {
        if (postList != null) {
            final Transaction transaction = new Transaction();
            try {
                if (!new ModelList<>(postList).saveAll(transaction)) {
                    // TODO: Log warning!
                    return;
                }

                final ModelList<HonyComment> commentsToSave = new ModelList<>();
                for (HonyPost honyPost : postList) {
                    final List<HonyComment> comments = honyPost.getComments();
                    if (comments != null) {
                        commentsToSave.addAll(comments);
                    }
                }

                if (!commentsToSave.saveAll(transaction)) {
                    // TODO: Log warning!
                    return;
                }

                transaction.setSuccessful(true);
            } finally {
                transaction.finish();
            }
        }
    }

    @Override
    public List<HonyPost> getPosts() {
        return Query.all(HonyPost.class).get().asList();
    }

    @Override
    public List<HonyComment> getComments(String postId) {
        final String sql = String.format("SELECT * FROM %s WHERE %s = ?",
                TABLE_COMMENTS.TABLE_NAME, TABLE_COMMENTS.COL_POST_ID);
        return Query.many(HonyComment.class, sql, postId).get().asList();
    }

    private class Version1DbMigration extends Migration {

        @Override
        protected void doMigration(SQLiteDatabase db) {
            db.execSQL("CREATE OR REPLACE TABLE " + TABLE_POSTS.TABLE_NAME + " ( " +
                    TABLE_POSTS.COL_ID + " TEXT PRIMARY KEY," +
                    TABLE_POSTS.COL_OBJECT_ID + " TEXT," +
                    TABLE_POSTS.COL_MESSAGE + " TEXT," +
                    TABLE_POSTS.COL_LINK + " TEXT," +
                    TABLE_POSTS.COL_PHOTO_URL + " TEXT," +
                    TABLE_POSTS.COL_CREATED + " INTEGER," +
                    TABLE_POSTS.COL_UPDATED + " INTEGER," +
                    TABLE_POSTS.COL_SHARES + " INTEGER," +
                    TABLE_POSTS.COL_LIKES + " INTEGER," +
                    TABLE_POSTS.COL_COMMENTS + " INTEGER" +
                    ")");

            db.execSQL("CREATE OR REPLACE TABLE " + TABLE_COMMENTS.TABLE_NAME + " ( " +
                    TABLE_COMMENTS.COL_ID + " TEXT PRIMARY KEY" +
                    TABLE_COMMENTS.COL_POST_ID + " TEXT," +
                    TABLE_COMMENTS.COL_MESSAGE + " TEXT," +
                    TABLE_COMMENTS.COL_LIKES + " INTEGER," +
                    TABLE_COMMENTS.COL_CREATED + " INTEGER," +
                    TABLE_COMMENTS.COL_FROM_NAME + " TEXT," +
                    TABLE_COMMENTS.COL_FROM_ID + " TEXT" +
                    "FOREIGN KEY(" + TABLE_COMMENTS.COL_POST_ID + ") " +
                    "REFERENCES " + TABLE_POSTS.TABLE_NAME + "(" + TABLE_POSTS.COL_ID + ") " +
                    "ON DELETE CASCADE" +
                    ")");
        }
    }
}