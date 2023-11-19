//package com.example.duan1bookapp.activities;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.duan1bookapp.databinding.FragmentHomeBinding;
//import com.example.duan1bookapp.models.Category;
//import com.example.duan1bookapp.retrofit.IComicAPI;
//import com.example.duan1bookapp.retrofit.RetrofitService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class HomeFragment extends Fragment {
//    private FragmentHomeBinding binding;
//    RetrofitService retrofitService = new RetrofitService();
//    public ViewPagerAdapter viewPagerAdapter;
//    private ArrayList<Category> categoryArrayList;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentHomeBinding.inflate(LayoutInflater.from(getContext()), container, false);
//        clickUnble();
//        setupViewPagerAdapter(binding.viewpager);
//        binding.tabLayout.setupWithViewPager(binding.viewpager);
//        return binding.getRoot();
//
//
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void clickUnble(){
//        binding.booksRv0.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (binding.searchEt.isFocused()) {
//                        Rect outRect = new Rect();
//                        binding.searchEt.getGlobalVisibleRect(outRect);
//                        if (!outRect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
//                            binding.searchEt.clearFocus();
//                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                        }
//                    }
//                }
//                return false;
//            }
//        });
//        binding.viewpager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (binding.searchEt.isFocused()) {
//                        Rect outRect = new Rect();
//                        binding.searchEt.getGlobalVisibleRect(outRect);
//                        if (!outRect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
//                            binding.searchEt.clearFocus();
//                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                        }
//                    }
//                }
//                return false;
//            }
//        });
//        binding.ln1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (binding.searchEt.isFocused()) {
//                        Rect outRect = new Rect();
//                        binding.searchEt.getGlobalVisibleRect(outRect);
//                        if (!outRect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
//                            binding.searchEt.clearFocus();
//                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                        }
//                    }
//                }
//                return false;
//            }
//        });
//
//    }
//
//    private void setupViewPagerAdapter(ViewPager viewPager) {
//        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, getContext());
//        categoryArrayList= new ArrayList<>();
//        retrofitService = new RetrofitService();
//
//        IComicAPI iComicAPI = retrofitService.getRetrofit().create(IComicAPI.class);
//
//        Call<List<Category>> categoryCall = iComicAPI.getCategories();
//        categoryCall.enqueue(new Callback<List<Category>>() {
//            @Override
//            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
//                if (response.isSuccessful()) {
//                    categoryArrayList = response.body();
//
//                    for (Category category : categoryArrayList) {
//                        viewPagerAdapter.addFragment(BooksUserFragment2.newInstance(
//                                String.valueOf(category.getId()),
//                                category.getCategoryName(),
//                                ""), category.getCategoryName());
//                    }
//
//                    viewPagerAdapter.notifyDataSetChanged();
//                } else {
//                    // Handle response errors
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Category>> call, Throwable t) {
//                // Handle API call failure
//            }
//        });
//
//        viewPager.setAdapter(viewPagerAdapter);
//    }
//
//    public class ViewPagerAdapter extends FragmentPagerAdapter {
//        private List<Fragment> fragmentList = new ArrayList<>();
//        private List<String> fragmentTitleList = new ArrayList();
//        private Context context;
//
//        public ViewPagerAdapter(FragmentManager fm, int behavior, Context context) {
//            super(fm, behavior);
//            this.context = context;
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            fragmentList.add(fragment);
//            fragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitleList.get(position);
//        }
//    }
//}
//
//
//
