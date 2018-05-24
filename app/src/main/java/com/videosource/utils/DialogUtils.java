package com.videosource.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.videosource.R;

public class DialogUtils {

    private static AlertDialog alert;

    public static void showDropDownListStrings(String title, Context context, final TextView textView, final String[] categoryNames, final View.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(categoryNames, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                alert.dismiss();
                if (textView != null) {
                    textView.setText(categoryNames[item]);
                    textView.setTag(categoryNames[item]);
                }
                if (clickListener != null) {
                    if (textView != null)
                        clickListener.onClick(textView);
                }
            }
        });
        alert = builder.create();
        alert.show();
    }

    public static void showDropDownListStrings(Context context, final String[] categoryNames, final View view, final View.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setItems(categoryNames, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                alert.dismiss();
                if (view != null) view.setTag(categoryNames[item]);
                if (clickListener != null) {
                    clickListener.onClick(view);
                }
            }
        });
        alert = builder.create();
        alert.show();
    }

    /*public static void showSimpleDialog(final Context mContext, final String message, final View.OnClickListener positiveClick, final View.OnClickListener negativeClick) {
        showSimpleDialog(mContext, null, message, null, null, positiveClick, negativeClick, false);
    }

    public static void showSimpleDialog(final Context mContext, final String message, final View.OnClickListener positiveClick, final View.OnClickListener negativeClick, final boolean singleButton) {
        showSimpleDialog(mContext, null, message, null, null, positiveClick, negativeClick, singleButton);
    }

    public static void showSimpleDialog(final Context mContext, final String heading, final String message, final String positiveText, final String negativeText, final View.OnClickListener positiveClick, final View.OnClickListener negativeClick, final boolean singleButton) {
        showSimpleDialog(mContext, heading, message, positiveText, negativeText, positiveClick, negativeClick, singleButton, true);
    }

    public static void showSimpleDialog(final Context mContext, final String heading, final String message, final String positiveText, final String negativeText, final View.OnClickListener positiveClick, final View.OnClickListener negativeClick, final boolean singleButton, final boolean isCancelable) {
        try {
            CustomTextView txtHeading, txtMessage, txtPositiveButton, txtNegativeButton;

            final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogCustom);
            alertDialog.setCancelable(false);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.layout_dialog);
            txtHeading = alertDialog.findViewById(R.id.txtHeading);
            txtMessage = alertDialog.findViewById(R.id.txtMessage);
            txtPositiveButton = alertDialog.findViewById(R.id.txtPositive);
            txtNegativeButton = alertDialog.findViewById(R.id.txtNegative);

            alertDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = alertDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            txtHeading.setText(TextUtils.isEmpty(heading) ? mContext.getString(R.string.app_name) : heading);
            txtMessage.setText(message);

            txtPositiveButton.setText(TextUtils.isEmpty(positiveText) ? mContext.getString(R.string.ok) : positiveText);

            if (singleButton) {
                txtNegativeButton.setVisibility(View.GONE);
            }

            txtNegativeButton.setText(TextUtils.isEmpty(negativeText) ? mContext.getString(R.string.close) : negativeText);

            txtPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (positiveClick != null) {
                        positiveClick.onClick(v);
                    }
                }
            });

            txtNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (negativeClick != null) {
                        negativeClick.onClick(v);
                    }
                }
            });

            alertDialog.setCancelable(isCancelable);
            alertDialog.setCanceledOnTouchOutside(isCancelable);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showForgotPasswordDialog(final Context mContext, final View.OnClickListener positiveClick) {
        try {
            final CustomTextView txtHeading, txtSend;
            final CustomEditText edtEmailAddress;
            final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogCustom);
            alertDialog.setCancelable(false);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.layout_forgot_password);
            txtHeading = alertDialog.findViewById(R.id.txtHeading);
            txtSend = alertDialog.findViewById(R.id.txtSend);
            edtEmailAddress = alertDialog.findViewById(R.id.edtEmailAddress);

            alertDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = alertDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            txtSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailId = edtEmailAddress.getText().toString().trim();
                    if (TextUtils.isEmpty(emailId)) {
                        edtEmailAddress.requestFocus();
                        showSimpleDialog(mContext, mContext.getString(R.string.please_enter_email_address), null, null, true);
                        return;
                    }
                    if (!StaticUtils.isValidEmail(emailId)) {
                        edtEmailAddress.requestFocus();
                        showSimpleDialog(mContext, mContext.getString(R.string.please_enter_a_valid_email_address), null, null, true);
                        return;
                    }

                    alertDialog.dismiss();
                    v.setTag(emailId);
                    if (positiveClick != null) {
                        positiveClick.onClick(v);
                    }
                }
            });

            txtHeading.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_RIGHT = 2;
//                    if(event.getAction()==MotionEvent.ACTION_BUTTON_RELEASE)
                    if (event.getRawX() >= (txtHeading.getRight() - txtHeading.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        alertDialog.dismiss();
                        return true;
                    }
                    return false;
                }
            });

            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imagePickerDialog(final Context mContext, final View.OnClickListener cameraClick, final View.OnClickListener galleryClick) {
        try {
            TextView txtCamera, txtGallery, txtCancel;
            final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogCustom);
            alertDialog.setCancelable(false);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.image_picker_dialog);
            txtCamera = alertDialog.findViewById(R.id.txtCamera);
            txtGallery = alertDialog.findViewById(R.id.txtGallery);
            txtCancel = alertDialog.findViewById(R.id.txtCancel);

            alertDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = alertDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            txtCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (cameraClick != null) {
                        cameraClick.onClick(v);
                    }
                }
            });
            txtGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (galleryClick != null) {
                        galleryClick.onClick(v);
                    }
                }
            });
            txtCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.setCancelable(true);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String language = "";

    public static void showLanguagePickerDialog(final Context mContext, final View.OnClickListener cameraClick) {
        language = "en";
        try {
            String[] languagesArray = new String[]{"English", "తెలుగు"};
            final CustomTextView txtLanguageChangeHint, txtProceed, txtSelectLanguage;
            Spinner spinnerLanguage;
            final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogCustom);
            alertDialog.setCancelable(false);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.layout_language_selection);

            txtLanguageChangeHint = alertDialog.findViewById(R.id.txtLanguageChangeHint);
            txtProceed = alertDialog.findViewById(R.id.txtProceed);
            txtSelectLanguage = alertDialog.findViewById(R.id.txtSelectLanguage);
            spinnerLanguage = alertDialog.findViewById(R.id.spinnerLanguage);
            ArrayAdapter spinnerAdapter = new ArrayAdapter(mContext, android.R.layout.simple_spinner_item, languagesArray);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLanguage.setAdapter(spinnerAdapter);

            spinnerLanguage.setSelection(0, true);
            spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        language = "en";
                        txtLanguageChangeHint.setText("You can change your language choice in settings at any time.");
                        txtProceed.setText("Proceed");
                        txtSelectLanguage.setText("Please Select Language");
                    } else if (i == 1) {
                        language = "tl";
                        txtLanguageChangeHint.setText("మీరు ఎప్పుడైనా మీ భాష ఎంపికను సెట్టింగ్ల్లో మార్చవచ్చు.");
                        txtProceed.setText("ముందుకు");
                        txtSelectLanguage.setText("దయచేసి భాషను ఎంచుకోండి");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = alertDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            txtProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LocalStorage.getInstance(mContext).putString(LocalStorage.PREF_LANGUAGE, language);
                    alertDialog.dismiss();
                    if (cameraClick != null) {
                        cameraClick.onClick(v);
                    }
                }
            });

            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showConfirmMobileNumberDialog(final Context mContext, final String message, final View.OnClickListener positiveClick) {
        try {
            final CustomTextView txtMobileNumber, txtEdit, txtConfirm;
            final Dialog alertDialog = new Dialog(mContext, R.style.AlertDialogCustom);
            alertDialog.setCancelable(false);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.layout_confirm_mobile_number);

            txtMobileNumber = alertDialog.findViewById(R.id.txtMobileNumber);
            txtEdit = alertDialog.findViewById(R.id.txtEdit);
            txtConfirm = alertDialog.findViewById(R.id.txtConfirm);

            txtMobileNumber.setText(message);

            alertDialog.getWindow().getAttributes().windowAnimations = R.style.AlertDialogCustom;

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = alertDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            txtConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (positiveClick != null) {
                        positiveClick.onClick(v);
                    }
                }
            });

            txtEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
