package com.example.btyisu.galleryproject.list;

/**
 * ItemListAdapter 와 SectionListData 에서 사용되는 데이터 리스트 타입 인터페이스
 * 컴파일 타임에 ItemData 의 자료형이 결정되지 않기 때문에 interface 를 이용하여 구현하게 됨
 */
public interface ItemData {
    /**
     * 뷰 타입 반환 메소드
     *
     * @return 뷰 타입
     */
    int getViewType();
}
