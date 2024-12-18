// Generated by view binder compiler. Do not edit!
package com.dicoding.instahelp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.dicoding.instahelp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySelectRoleBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ButtonOrangeBinding btnNextSelectRole;

  @NonNull
  public final LinearLayout cardInstance;

  @NonNull
  public final LinearLayout cardResident;

  @NonNull
  public final LinearLayout roleSelectionContainer;

  @NonNull
  public final View toggleInstance;

  @NonNull
  public final View toggleResident;

  @NonNull
  public final TextView tvHeaderSubtitle;

  @NonNull
  public final TextView tvHeaderTitle;

  private ActivitySelectRoleBinding(@NonNull RelativeLayout rootView,
      @NonNull ButtonOrangeBinding btnNextSelectRole, @NonNull LinearLayout cardInstance,
      @NonNull LinearLayout cardResident, @NonNull LinearLayout roleSelectionContainer,
      @NonNull View toggleInstance, @NonNull View toggleResident,
      @NonNull TextView tvHeaderSubtitle, @NonNull TextView tvHeaderTitle) {
    this.rootView = rootView;
    this.btnNextSelectRole = btnNextSelectRole;
    this.cardInstance = cardInstance;
    this.cardResident = cardResident;
    this.roleSelectionContainer = roleSelectionContainer;
    this.toggleInstance = toggleInstance;
    this.toggleResident = toggleResident;
    this.tvHeaderSubtitle = tvHeaderSubtitle;
    this.tvHeaderTitle = tvHeaderTitle;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySelectRoleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySelectRoleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_select_role, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySelectRoleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_next_select_role;
      View btnNextSelectRole = ViewBindings.findChildViewById(rootView, id);
      if (btnNextSelectRole == null) {
        break missingId;
      }
      ButtonOrangeBinding binding_btnNextSelectRole = ButtonOrangeBinding.bind(btnNextSelectRole);

      id = R.id.card_instance;
      LinearLayout cardInstance = ViewBindings.findChildViewById(rootView, id);
      if (cardInstance == null) {
        break missingId;
      }

      id = R.id.card_resident;
      LinearLayout cardResident = ViewBindings.findChildViewById(rootView, id);
      if (cardResident == null) {
        break missingId;
      }

      id = R.id.role_selection_container;
      LinearLayout roleSelectionContainer = ViewBindings.findChildViewById(rootView, id);
      if (roleSelectionContainer == null) {
        break missingId;
      }

      id = R.id.toggle_instance;
      View toggleInstance = ViewBindings.findChildViewById(rootView, id);
      if (toggleInstance == null) {
        break missingId;
      }

      id = R.id.toggle_resident;
      View toggleResident = ViewBindings.findChildViewById(rootView, id);
      if (toggleResident == null) {
        break missingId;
      }

      id = R.id.tv_header_subtitle;
      TextView tvHeaderSubtitle = ViewBindings.findChildViewById(rootView, id);
      if (tvHeaderSubtitle == null) {
        break missingId;
      }

      id = R.id.tv_header_title;
      TextView tvHeaderTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvHeaderTitle == null) {
        break missingId;
      }

      return new ActivitySelectRoleBinding((RelativeLayout) rootView, binding_btnNextSelectRole,
          cardInstance, cardResident, roleSelectionContainer, toggleInstance, toggleResident,
          tvHeaderSubtitle, tvHeaderTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
