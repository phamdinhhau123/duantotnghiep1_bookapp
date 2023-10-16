package com.example.duan1bookapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import com.example.duan1bookapp.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(getContext()), container, false);
        clickUnble();
        return binding.getRoot();

    }
    @SuppressLint("ClickableViewAccessibility")
    private void clickUnble(){
        binding.booksRv0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (binding.searchEt.isFocused()) {
                        Rect outRect = new Rect();
                        binding.searchEt.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
                            binding.searchEt.clearFocus();
                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });
        binding.frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (binding.searchEt.isFocused()) {
                        Rect outRect = new Rect();
                        binding.searchEt.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
                            binding.searchEt.clearFocus();
                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });
        binding.ln1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (binding.searchEt.isFocused()) {
                        Rect outRect = new Rect();
                        binding.searchEt.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
                            binding.searchEt.clearFocus();
                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });

    }
}