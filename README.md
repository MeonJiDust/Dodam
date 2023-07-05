# 💡주제

#### ▸ 부모와 자녀의 커뮤니케이션을 돕는 어플리케이션<br/><br/>

# 📝 배경

유튜브와 같은 인터넷 매체들로 인해 아이들의 스마트폰 사용량이 늘어, 어린 아이들과 부모님 간의 유대관계가 낮아지고 있다고 판단.

유대관계를 쌓기 위해 아이들과 부모님이 함께 갈 수 있는 여러 장소들을 추천해주고, 그 추억을 함께 공유하고, 나중에 그런 추억들을 회상할 수 있는 공간이 있었으면 좋겠다라는 생각이 들어 이런 기능들이 있는 어플리케이션을 개발하게 됨.
<br/><br/>

# 📝 주요 Activity

▸ 첫번째 탭에선 수행할 미션을 등록할 수 있고, 미션 성공/실패에 따른 리스트가 각각 생성된다.

두번째 탭에선 등록한 미션을 수행하며 찍었던 사진들을 캘린더에 날짜별로 저장할 수 있다. 

<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/3118256c-3d96-40fb-aa38-4fcaf6cf926a"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/a6cb3806-17d0-4e1c-91e1-5b74c24e4015"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/d1cd5332-3ccb-43fa-a9d7-ea5c97c36b55"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/a608f5d0-006a-4a70-b8c6-9c6957a809b1"  width="200" height="400"><br/><br/>

▸ 지도의 핀을 누르면 전체 사용자들이 서로 실시간으로 추억을 공유할 수 있게끔 파이어베이스를 연동한 리스트 창 제공

<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/66a63b78-a190-4bf4-a637-4f7664405452"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/6dd6b6a4-655b-4244-ac48-7cfa7649a902"  width="200" height="400">
<img src="https://github.com/MeonJiDust/Dodam/assets/90547127/1633f51f-1fec-4366-8340-c04eaca810da"  width="200" height="400"><br/><br/>


# ⭐️ 주요 코드 리뷰

▸ 안드로이드 스튜디오의 내장 DB인 SQLiteDatabase를 이용하여 미션 등록 시 사용한 buckets테이블에서 idx, title, memo 속성을 가져와 리스트에 뿌려준다.
```
DBHelper2 helper = new DBHelper2(getContext());
SQLiteDatabase db = helper.getWritableDatabase();

String upSql = "SELECT  * from buckets ";

/*...*/

                sList.add(new Memo(idx, title, memo));
/*...*/
```
<br/><br/>
▸ 리스트의 아이템을 한 줄에 3개씩 보여주기 위해 ListView가 아닌 RecyclerView를 사용하였다. 

또한, 리스트에 띄울 데이터가 존재하지 않는다면 '데이터가 존재하지 않습니다' 텍스트를 띄울 수 있도록 조건문을 작성하였다.

```
recyclerView = findViewById(R.id.viv);
GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
recyclerView.setLayoutManager(gridLayoutManager);

recyclerView.addItemDecoration(new RecyclerDecoration(this));

nodata = (TextView) findViewById(R.id.nodata);

if (sList.size() > 0) {
  nodata.setVisibility(View.GONE);
} else {
  nodata.setVisibility(View.VISIBLE);
}
```
<br/><br/>
▸ 폴라로이드 리스트 화면에서 아이템을 길게 누르면 수정 / 삭제 다이얼로그가 띄워지고 삭제를 누르면, SQLiteDatabase에 저장되어있던 해당 포지션의 데이터가 삭제된다.

```

view.setOnLongClickListener(new View.OnLongClickListener() {
  @Override
  public boolean onLongClick(View v) {
    AlertDialog.Builder adialog = new AlertDialog.Builder(Polaroid.this);
    adialog.setIcon(R.drawable.user);
    adialog.setPositiveButton("수정",new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog,int which) {
        /*...*/
      }
    }).setNegativeButton("삭제",new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog,int which) {
        dialog.dismiss();
        deleteDB(listdata.get(position).getIdx()); //선택된 곳의 인덱스 값을 넘겨줘서 내장 DB에서 데이터 삭제
      }
    });

//DB삭제 코드

DBHelper1 helper = new DBHelper1(getBaseContext());
SQLiteDatabase db = helper.getWritableDatabase(); // DB를 오픈한다
try {
  db.delete("bucket", "_id='" + idx + "'", null); // bucket테이블의 해당 인덱스 삭제
} catch (Exception e) {
  /*...*/
```
<br/><br/>
# 🤔 배운점

▸ recyclerview를 이용해서 단순한 세로형태의 리스트 뿐만 아니라, 가로 리스트 구현법과 리스트 레이아웃을 Gird로 바꿔 한 줄에 여러 아이템을 띄우는 방법을 알게되었다.

▸ SQLiteDatabase라는 안드로이드 스튜디오의 내장 DB를 알게되었다.

▸ 첫 어플리케이션 개발이고, 안드로이드 스튜디오라는 툴을 처음 써봐서 클래스명, 변수명 등이 통일성 없이 뒤죽박죽인데, <br/>이런 코드는 후에 코드를 보았을 때 가독성이 떨어지고 어떤 코드인지 파악하기 어려워지므로 클린코드의 중요성을 깨닫게 되었다.

▸ 이미지를 DB에 저장하려면 Bitmap라이브러리를 사용해 Uri로 변환해야한다.
