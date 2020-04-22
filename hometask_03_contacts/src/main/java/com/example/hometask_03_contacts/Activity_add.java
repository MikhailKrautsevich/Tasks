package com.example.hometask_03_contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Activity_add extends Activity {

    private EditText nameAddText, phoneNumberAdd, emailAdd ;
    private RadioButton phoneNumberRadButton, emailRadButton ;
    Intent answerIntent ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        answerIntent = new Intent() ;

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.email_Or_phoneNumber);
        phoneNumberRadButton = findViewById(R.id.phoneNumberRadButton) ;
        emailRadButton = findViewById(R.id.emailRadButton) ;
        radioGroup.addView(phoneNumberRadButton);
        radioGroup.addView(emailRadButton);

        nameAddText = findViewById(R.id.nameAddText) ;
        phoneNumberAdd = findViewById(R.id.phoneNumberAdd) ;
        emailAdd = findViewById(R.id.emailAdd) ;
        emailAdd.setVisibility(View.INVISIBLE);

        ImageButton backFromAdd = findViewById(R.id.backFromAdd);
        backFromAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, answerIntent);
                finish();
            }
        });
        ImageButton addConfirm = findViewById(R.id.addConfirm);
        addConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phoneNumberRadButton.isChecked() && !emailRadButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Выберите тип контакта", Toast.LENGTH_SHORT)
                            .show();
                }
                if (phoneNumberRadButton.isChecked() && !emailRadButton.isChecked() ){
                    Toast.makeText(getApplicationContext(), "Будем сохранять номер телефона", Toast.LENGTH_SHORT)
                            .show();
                    if (answerIntent != null) {
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_NAME, nameAddText.getText()
                        .toString()) ;
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_INFO, phoneNumberAdd.getText()
                        .toString());
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_IS, false) ;
                    }
                    setResult(RESULT_OK, answerIntent);
                    finish();
                }
                if (!phoneNumberRadButton.isChecked() &&  emailRadButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Будем сохранять емайл", Toast.LENGTH_SHORT)
                            .show();
                    if (answerIntent != null) {
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_NAME, nameAddText.getText()
                                .toString()) ;
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_INFO, emailAdd.getText()
                                .toString());
                        answerIntent.putExtra(EXTRAS.EXTRA_FOR_CONTACT_IS, true) ;
                    }
                    setResult(RESULT_OK, answerIntent);
                    finish();
                }
                if ( phoneNumberRadButton.isChecked() && emailRadButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Ты определись", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    public void onRadioClick(View view) {
        switch (view.getId()) {
            case R.id.phoneNumberRadButton:
                phoneNumberAdd.setVisibility(View.VISIBLE);
                emailAdd.setVisibility(View.INVISIBLE);
                break;
            case R.id.emailRadButton:
                phoneNumberAdd.setVisibility(View.INVISIBLE);
                emailAdd.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
