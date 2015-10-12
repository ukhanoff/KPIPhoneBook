package com.example.kpiphonebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PBActivity extends Activity {

	final int PB_DIALOG_ON_PHONE_CLICK = 1;
	final int PB_DIALOG_ON_ITEM_CLICK=0;
	
	final int PB_NUMBER_ORGS = 300;
	final String PB_ITEM_POSADA = "pb_item_posada";
	final String PB_ITEM_NAME = "pb_item_name";
	final String PB_ITEM_PHONE = "pb_item_phone";
	final String PB_ITEM_ORG = "pb_item_org";
	
	String[] from = {PB_ITEM_ORG, PB_ITEM_POSADA, PB_ITEM_NAME, PB_ITEM_PHONE};
	int[] to = {R.id.pb_item_org, R.id.pb_item_posada, R.id.pb_item_name, R.id.pb_item_phone};
	
	/** ID's array of organizations: 300 items */
	int[] org = {R.array.org_id_1, R.array.org_id_2, R.array.org_id_3, R.array.org_id_4, R.array.org_id_5, 
			R.array.org_id_6, R.array.org_id_7, R.array.org_id_8, R.array.org_id_9, R.array.org_id_10,
			R.array.org_id_11, R.array.org_id_12, R.array.org_id_13, R.array.org_id_14,
			R.array.org_id_15, R.array.org_id_16, R.array.org_id_17, R.array.org_id_18,
			R.array.org_id_19, R.array.org_id_20, R.array.org_id_21, R.array.org_id_22,
			R.array.org_id_23, R.array.org_id_24, R.array.org_id_25, R.array.org_id_26, R.array.org_id_27, R.array.org_id_28, R.array.org_id_29, R.array.org_id_30, R.array.org_id_31, R.array.org_id_32, R.array.org_id_33, R.array.org_id_34, R.array.org_id_35, R.array.org_id_36, R.array.org_id_37, R.array.org_id_38, R.array.org_id_39, R.array.org_id_40, R.array.org_id_41, R.array.org_id_42, R.array.org_id_43, R.array.org_id_44, R.array.org_id_45, R.array.org_id_46, R.array.org_id_47, R.array.org_id_48, R.array.org_id_49, R.array.org_id_50, R.array.org_id_51, R.array.org_id_52, R.array.org_id_53, R.array.org_id_54, R.array.org_id_55, R.array.org_id_56, R.array.org_id_57, R.array.org_id_58, R.array.org_id_59, R.array.org_id_60, R.array.org_id_61, R.array.org_id_62, R.array.org_id_63, R.array.org_id_64, R.array.org_id_65, R.array.org_id_66, R.array.org_id_67, R.array.org_id_68, R.array.org_id_69, R.array.org_id_70, R.array.org_id_71, R.array.org_id_72, R.array.org_id_73, R.array.org_id_74, R.array.org_id_75, R.array.org_id_76, R.array.org_id_77, R.array.org_id_78, R.array.org_id_79, R.array.org_id_80, R.array.org_id_81, R.array.org_id_82, R.array.org_id_83, R.array.org_id_84, R.array.org_id_85, R.array.org_id_86, R.array.org_id_87, R.array.org_id_88, R.array.org_id_89, R.array.org_id_90, R.array.org_id_91, R.array.org_id_92, R.array.org_id_93, R.array.org_id_94, R.array.org_id_95, R.array.org_id_96, R.array.org_id_97, R.array.org_id_98, R.array.org_id_99, R.array.org_id_100, R.array.org_id_101, R.array.org_id_102, R.array.org_id_103, R.array.org_id_104, R.array.org_id_105, R.array.org_id_106, R.array.org_id_107, R.array.org_id_108, R.array.org_id_109, R.array.org_id_110, R.array.org_id_111, R.array.org_id_112, R.array.org_id_113, R.array.org_id_114, R.array.org_id_115, R.array.org_id_116, R.array.org_id_117, R.array.org_id_118, R.array.org_id_119, R.array.org_id_120, R.array.org_id_121, R.array.org_id_122, R.array.org_id_123, R.array.org_id_124, R.array.org_id_125, R.array.org_id_126, R.array.org_id_127, R.array.org_id_128, 
			R.array.org_id_129, R.array.org_id_130, R.array.org_id_131, R.array.org_id_132, R.array.org_id_133, R.array.org_id_134, R.array.org_id_135, R.array.org_id_136, R.array.org_id_137, R.array.org_id_138, R.array.org_id_139, R.array.org_id_140, R.array.org_id_141, R.array.org_id_142, R.array.org_id_143, R.array.org_id_144, R.array.org_id_145, R.array.org_id_146, R.array.org_id_147, R.array.org_id_148, R.array.org_id_149, R.array.org_id_150, R.array.org_id_151, R.array.org_id_152, R.array.org_id_153, R.array.org_id_154, R.array.org_id_155, R.array.org_id_156, R.array.org_id_157, R.array.org_id_158, R.array.org_id_159, R.array.org_id_160, R.array.org_id_161, R.array.org_id_162, R.array.org_id_163, R.array.org_id_164, R.array.org_id_165, R.array.org_id_166, R.array.org_id_167, R.array.org_id_168, R.array.org_id_169, R.array.org_id_170, R.array.org_id_171, R.array.org_id_172, R.array.org_id_173, R.array.org_id_174, R.array.org_id_175, R.array.org_id_176, R.array.org_id_177, R.array.org_id_178, R.array.org_id_179, R.array.org_id_180, R.array.org_id_181, R.array.org_id_182, R.array.org_id_183, R.array.org_id_184, R.array.org_id_185, R.array.org_id_186, R.array.org_id_187, R.array.org_id_188, R.array.org_id_189, R.array.org_id_190, R.array.org_id_191, R.array.org_id_192, R.array.org_id_193, R.array.org_id_194, R.array.org_id_195, R.array.org_id_196, R.array.org_id_197, R.array.org_id_198, R.array.org_id_199, R.array.org_id_200, R.array.org_id_201, R.array.org_id_202, R.array.org_id_203, R.array.org_id_204, R.array.org_id_205, R.array.org_id_206, R.array.org_id_207, R.array.org_id_208, R.array.org_id_209, R.array.org_id_210, R.array.org_id_211, R.array.org_id_212, R.array.org_id_213, R.array.org_id_214, R.array.org_id_215, R.array.org_id_216, R.array.org_id_217, R.array.org_id_218, R.array.org_id_219, R.array.org_id_220, R.array.org_id_221, R.array.org_id_222, R.array.org_id_223, R.array.org_id_224, R.array.org_id_225, R.array.org_id_226, R.array.org_id_227, R.array.org_id_228, R.array.org_id_229, R.array.org_id_230, R.array.org_id_231, R.array.org_id_232, R.array.org_id_233, R.array.org_id_234, R.array.org_id_235, R.array.org_id_236, R.array.org_id_237, R.array.org_id_238, R.array.org_id_239, R.array.org_id_240, R.array.org_id_241, R.array.org_id_242, R.array.org_id_243, R.array.org_id_244, R.array.org_id_245, R.array.org_id_246, R.array.org_id_247, R.array.org_id_248, R.array.org_id_249, R.array.org_id_250, R.array.org_id_251, R.array.org_id_252, R.array.org_id_253, R.array.org_id_254, R.array.org_id_255, R.array.org_id_256, R.array.org_id_257, R.array.org_id_258, R.array.org_id_259, R.array.org_id_260, R.array.org_id_261, R.array.org_id_262, R.array.org_id_263, R.array.org_id_264, R.array.org_id_265, R.array.org_id_266, R.array.org_id_267, R.array.org_id_268, R.array.org_id_269, R.array.org_id_270, R.array.org_id_271, R.array.org_id_272, R.array.org_id_273, R.array.org_id_274, R.array.org_id_275, R.array.org_id_276, R.array.org_id_277, R.array.org_id_278, R.array.org_id_279, R.array.org_id_280, R.array.org_id_281, R.array.org_id_282, R.array.org_id_283, R.array.org_id_284, R.array.org_id_285, R.array.org_id_286, R.array.org_id_287, R.array.org_id_288, R.array.org_id_289, R.array.org_id_290, R.array.org_id_291, R.array.org_id_292, R.array.org_id_293, R.array.org_id_294, R.array.org_id_295, R.array.org_id_296, R.array.org_id_297, R.array.org_id_298, R.array.org_id_299, R.array.org_id_300};
	
	ListView pbLv;
	EditText pbSt;
	
	SimpleAdapter pbSa;
	Resources res;
	ArrayList<Map<String, Object>> pbData;
	
	String[] pbOrgArray;
	
	final CharSequence[] phoneDialogItems = {
	    	"Phone",
	    	"Copy",
	    	"Share",
	    	"Back"
	    };
	
	ClipboardManager clipboard;
	InputMethodManager imm;
	
	String phone;
	String name;
	String posada;
	String[] str;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pb);
		
		clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        res = getResources();
        
        pbLv = (ListView) findViewById(R.id.listViewMain);
        pbSt = (EditText) findViewById(R.id.pb_search_et);
        pbLv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				itemClick(arg0, v, position, id);
			}
		});
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(pbSt.getWindowToken(), 0);
        pbSt.setOnEditorActionListener(new OnEditorActionListener() {
			
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				pbSbOnClick(v);
				return false;
			}
		});
        
        showAll();
	}
	
	void showAll(){
    	pbData = new ArrayList<Map<String,Object>>();
    	
    	for (int k=0;k<PB_NUMBER_ORGS;k++){
    		addToDataOrg(k);
    	}
        
        pbSa = new SimpleAdapter(this, pbData, R.layout.pb_item, from, to);
        pbLv.setAdapter(pbSa);
    }
    
    public void pbSbOnClick(View view){
    	String search_str = pbSt.getText().toString();
    	if (search_str==null){
    		showAll();
    	}
    	else{
    		pbData = new ArrayList<Map<String,Object>>();
    		
    		for (int i=0;i<PB_NUMBER_ORGS;i++){
    			pbOrgArray = res.getStringArray(org[i]);
    			
    			/**search in name of organization: true - add all organization; else - search inside organization*/
    			if (pbOrgArray[0].toLowerCase().indexOf(search_str.toLowerCase())>-1){
    				addToDataOrg(i);
    			}
    			else{//inside
    				boolean flag=false; //need, if we have more than 1 result in 1 org
    				Map<String, Object> m;
    				for (int j=0;j+3<pbOrgArray.length;j+=3){
    					
    					if ((pbOrgArray[j+1].toLowerCase().indexOf(search_str.toLowerCase())>-1)||(pbOrgArray[j+2].toLowerCase().indexOf(search_str.toLowerCase())>-1)||(pbOrgArray[j+3].toLowerCase().indexOf(search_str.toLowerCase())>-1)){
    						if (!flag){
    							m  = new HashMap<String, Object>();
    							m.put(PB_ITEM_ORG, pbOrgArray[0]);
    					        m.put(PB_ITEM_NAME, "");
    					        m.put(PB_ITEM_PHONE, "");
    					        m.put(PB_ITEM_POSADA, "");
    					        pbData.add(m);
    					        flag=true;
    						}
    						m= new HashMap<String, Object>();
    			            m.put(PB_ITEM_ORG, "");
    			            m.put(PB_ITEM_POSADA, pbOrgArray[j+1]);
    			            m.put(PB_ITEM_NAME, pbOrgArray[j+2]);
    			            m.put(PB_ITEM_PHONE, pbOrgArray[j+3]);
    			            pbData.add(m);
    					}
    				}
    			}
    		}
    		
    		pbSa = new SimpleAdapter(this, pbData, R.layout.pb_item, from, to);
            
            pbLv.setAdapter(pbSa);
    	}
    }
    
    void addToDataOrg(int id){
    	pbOrgArray = res.getStringArray(org[id]);

    	Map<String, Object> m = new HashMap<String, Object>();
        m.put(PB_ITEM_ORG, pbOrgArray[0]);
        m.put(PB_ITEM_NAME, "");
        m.put(PB_ITEM_PHONE, "");
        m.put(PB_ITEM_POSADA, "");
        pbData.add(m);
        for (int i=0; i+3<pbOrgArray.length;i+=3){
            m= new HashMap<String, Object>();
            m.put(PB_ITEM_ORG, "");
            m.put(PB_ITEM_POSADA, pbOrgArray[i+1]);
            m.put(PB_ITEM_NAME, pbOrgArray[i+2]);
            m.put(PB_ITEM_PHONE, pbOrgArray[i+3]);
            pbData.add(m);
        }
    }
    
    private void itemClick(AdapterView<?> arg0, View v, int position, long id){
    	Map<String, Object> m = pbData.get(position);
    	name = (String)m.get(PB_ITEM_NAME);
    	posada = (String)m.get(PB_ITEM_POSADA);
    	phone = (String)m.get(PB_ITEM_PHONE);
    	
    	//Toast.makeText(this, (String)m.get(PB_ITEM_PHONE), Toast.LENGTH_SHORT).show();
    	if (!phone.equals("")){showDialog(PB_DIALOG_ON_ITEM_CLICK);}
    }
    
    @Override
    protected Dialog onCreateDialog(final int id) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Choise:");
    	switch (id) {
		case PB_DIALOG_ON_ITEM_CLICK:{
	    	builder.setItems(phoneDialogItems, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					
					switch (which){
					//Phone
					case 0:{
						Phone();
						break;
					}
					//Copy
					case 1:{
						Copy();
						break;
					}
					//Share
					case 2:{
						Share();
						break;
					}
					default:{}
					}
				}
			});
	    	return builder.create();}
		case PB_DIALOG_ON_PHONE_CLICK:{
			builder.setItems(str, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {	
					phone=str[which];
					if (phone.contains("ф."))
						makeMessShort("Can\'t phone to fax number");
					else{
						if (phone.length()==7){
							makeMessLong("Phone to (044)"+phone);
							callPhone("044"+phone);
						}else{
							if (phone.length()==4){
								makeMessLong("Phone to (044)454"+phone);
								callPhone("044454"+phone);
							}else
								makeMessLong("If you see this, you found a bug. Please, tell us about it.");
						}
					}
				}
			});
	    	return builder.create();}

		default:
			return null;
		}
    	
    }
    
    private void Share() {
    	final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		/*intent.putExtra(Intent.EXTRA_SUBJECT,
				"Find a nice app for android #kpiguide:");*/
		intent.putExtra(Intent.EXTRA_TEXT, posada+", "+name+"\nТелефон: "+phone);
		startActivity(Intent.createChooser(intent,
				getString(R.string.app_name)));
	}

	private void Copy() {
		clipboard.setText("Посада:"+posada+"\nП.І.Б.: "+name+"\nТелефон: "+phone);
	}

	private void Phone() {
		// TODO Phone to contact: for many number make dialog. Make parse.
		
		str = null;
		str = phone.split("т./ф.");//delete t./f.
		phone = str[0];
		for (int i=1; i<str.length; i++)
			phone+=str[i];
		
		str = phone.split("-");//delete all -
		phone = str[0];
		for (int i=1;i<str.length;i++)
			phone+=str[i];
		
		str = phone.split("\\D+");//split by numbers? 
		Log.e("phone", phone);
		if (str.length>1){
			//Log.e("str[0]", str[0]);
			removeDialog(PB_DIALOG_ON_PHONE_CLICK);
			showDialog(PB_DIALOG_ON_PHONE_CLICK);
	    }
		else{
			phone=str[0];
			//Log.e("str[0]", str[0]);
			if (phone.contains("ф."))
				makeMessShort("Can\'t phone to fax number");
			else{
				if (phone.length()==7){
					makeMessLong("Phone to (044)"+phone);
					callPhone("044"+phone);
				}else{
					if (phone.length()==4){
						makeMessLong("Phone to (044)454"+phone);
						callPhone("044454"+phone);
					}else
						makeMessLong("If you see this, you found a bug. Please, tell us about it.");
				}
			}
		}
	}
	
	private void callPhone(String number){
		try{
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:"+number));
			startActivity(callIntent);
		} catch (ActivityNotFoundException e) {
			Log.e("phone to number", "Call failed to: "+number, e);
		}
	}
	
	void makeMessLong(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
	void makeMessShort(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
