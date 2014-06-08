package jp.test.fndummy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private String sync_mode;
	MyBroadcastReceiver ｒeceiver;
	IntentFilter intentFilter;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        sync_mode = getIntent().getStringExtra("sync_mode");
    	Intent broadcastIntent = new Intent();
        
    	
    	if (sync_mode != null){
        	ｒeceiver = new MyBroadcastReceiver();
            intentFilter = new IntentFilter();
            intentFilter.addAction("jibe.android.notification.2WAY_SYNC_RESULT");
            intentFilter.addAction("jibe.android.notification.FETCH_NUMBER_RESULT");
            intentFilter.addAction("jibe.android.notification.BACKUP_STATUS_RESULT");
            
            registerReceiver(ｒeceiver, intentFilter);      		
    		
	        if (sync_mode.equals("2WaySync")){
	            broadcastIntent.setAction("jibe.android.notification.2WAY_SYNC_RESULT");
	        } else if (sync_mode.equals("fetchNumber")) {
	            broadcastIntent.setAction("jibe.android.notification.FETCH_NUMBER_RESULT");
	        } else if (sync_mode.equals("statusCheck")) {
	            broadcastIntent.setAction("jibe.android.notification.BACKUP_STATUS_RESULT");
	        }
	        
	        getBaseContext().sendBroadcast(broadcastIntent);
    	}
    	
   	

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);
            return rootView;
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver{
    	@Override
    	public void onReceive(Context context, Intent i){
    	    Bundle bundle = i.getExtras();
//    	    String message = bundle.getString("message");
    	    LoginActivity.this.setResult(RESULT_OK,i);
    	    finish();
//    	    Toast.makeText(
//    	      context, 
//    	      "onReceive! " + message, 
//    	      Toast.LENGTH_LONG).show();

    	}
    }	
    
}
