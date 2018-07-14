package pe.edu.upc.tarea6;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.tarea6.models.Employee;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText inputFirstName, inputLastName,inputUserName, inputPassword;
    private TextInputLayout inputLayoutFirstName, inputLayoutLastName, inputLayoutUserName, inputLayoutPassword;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());
        /*Elementos de etiquetas en el formulario*/
        inputLayoutFirstName = (TextInputLayout) findViewById(R.id.input_layout_fname);
        inputLayoutLastName = (TextInputLayout) findViewById(R.id.input_layout_lname);
        inputLayoutUserName = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        /*Elementos de entrada en el formulario*/
        inputFirstName = (EditText) findViewById(R.id.input_fname);
        inputLastName = (EditText) findViewById(R.id.input_lname);
        inputUserName = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnSave = (Button) findViewById(R.id.btn_signup);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username     = inputUserName.getText().toString().trim();
                String password     = inputPassword.getText().toString().trim();
                String firstName    = inputFirstName.getText().toString().trim();
                String lastName     = inputLastName.getText().toString().trim();
                /* Creando objeto Empleado usando el constructor con sus 4 entradas*/
                Employee employee = new Employee(username,password,firstName,lastName);
                /* Validando que campos*/
                if (employee.getFirstName().isEmpty()) {
                    inputLayoutFirstName.setError(getString(R.string.err_msg_first_name));
                    requestFocus(inputFirstName);
                    return;
                }
                if (employee.getLastName().isEmpty()) {
                    inputLayoutLastName.setError(getString(R.string.err_msg_last_name));
                    requestFocus(inputLastName);
                    return;
                }
                if (employee.getUsername().isEmpty()) {
                    inputLayoutUserName.setError(getString(R.string.err_msg_email));
                    requestFocus(inputUserName);
                    return;
                }
                if (employee.getPassword().isEmpty()) {
                    inputLayoutPassword.setError(getString(R.string.err_msg_password));
                    requestFocus(inputPassword);
                    return;
                }
                /*Una vez validado los campos procedemos a enviar el objeto al endpoint correspondiente*/
                saveNewEmployee(employee,getApplicationContext());
            }
        });
    }

    public  void saveNewEmployee(Employee employee, final Context context){
            String urlAPI = "http://178.128.155.0/apiv1/public";
            AndroidNetworking.post(urlAPI+"/user")
                    .addBodyParameter(employee)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response
                            Log.d("tag", response.toString());
                            try {
                                int userIdResponse = Integer.valueOf(response.get("user_id").toString());
                                String messageResponse = response.get("msg").toString();
                                alertMessage(messageResponse,context);
                                if(userIdResponse!=0){
                                    refreshForm();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });
        }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static void alertMessage(String message,Context context){
        Toast toast1 = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER,0,0);
        toast1.show();
    }

    private  void refreshForm(){
        inputUserName.setText("");
        inputPassword.setText("");
        inputFirstName.setText("");
        inputLastName.setText("");
    }

}
