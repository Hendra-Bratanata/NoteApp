package Support;

import android.view.View;

public class CostomOnItemClickListener implements View.OnClickListener {
    private int pos;
    private OnItemClickCallBack onItemClickCallBack;

    public CostomOnItemClickListener(int pos,OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
        this.pos =pos;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallBack.onItemClicked(v,pos);
    }

 public interface OnItemClickCallBack{
        void onItemClicked(View view,int pos);
 }
}
