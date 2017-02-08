package kiam.locationphotoboard;

import android.content.Context;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.IDataStore;

/**
 * Created by moeg on 2017-02-03.
 */

public class PostManager {
    private static final IDataStore<Post> DATA_STORE = Backendless.Persistence.of( Post.class );

    public static void saveEntity(final Post entity, Context context, final InnerCallBack<Post> callback )
    {
        Log.d("PostManager","Outside of the Data_Store.save");
        DATA_STORE.save( entity, new DefaultCallback<Post>( context )
        {

            @Override
            public void handleResponse( Post response )
            {
                Log.d("PostManager","Inside of the Data_Store.save");

                super.handleResponse( response );

                if( callback != null )
                    callback.handleResponse( response );
            }
        } );
    }




}
