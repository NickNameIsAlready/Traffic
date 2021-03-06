package traffic.traffic1.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import traffic.traffic1.R;
import traffic.traffic1.fragment.LightMangerFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    public Toolbar getToolbar(){
        return toolbar;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.huanjingzhibiao:
                startActivity(new Intent(this, EnvirActivity.class));
                break;
            case R.id.zhangdanguanli:
                startActivity(new Intent(this, BillActivity.class));
                break;
            case R.id.chuanganqijilu:
                startActivity(new Intent(this, SensorHistoryActivity.class));
                break;
            case R.id.zhanghuchongzhi:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
            case R.id.shezhiyuzhi:
                startActivity(new Intent(this, SetThresholdActivity.class));
                break;
            case R.id.alert://lamp
                startActivity(new Intent(this, AlertActivity.class));
                break;
            case R.id.lamp://BusCount
                startActivity(new Intent(this, LampActivity.class));
                break;
            case R.id.BusCount://state
                startActivity(new Intent(this,BusCountActivity.class));
                break;
            case R.id.state://state
                startActivity(new Intent(this,StateActivity.class));
                break;
            case R.id.news://Mycar
                startActivity(new Intent(this,news.class));
                break;
            case R.id.Mycar://Mycar
                startActivity(new Intent(this,MyCarActivity.class));
                break;
            case R.id.diary://diary
                startActivity(new Intent(this,DiaryActivity.class));
                break;
            case R.id.daoluzhuangtai:
                startActivity(new Intent(this, RoadStatusActivity.class));
                break;
            case R.id.lamp_manger:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new LightMangerFragment()).commit();
                break;
//            case R.id.traffic://diary
//                startActivity(new Intent(this,TrafficActivity.class));
//                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
