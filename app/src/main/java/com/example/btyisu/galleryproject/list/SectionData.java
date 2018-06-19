package com.example.btyisu.galleryproject.list;

/**
 * SectionListData 에서 사용되는 데이터 리스트 타입 인터페이스
 * 컴파일 타임에 SectionData 의 자료형이 결정되지 않기 때문에 interface 를 이용하여 구현하게 됨
 */
public interface SectionData<T extends ItemData> extends ItemData {
    /**
     * position 에 따른 ItemData 반환 메소드
     *
     * @param position 요청된 ItemData 의 위치
     *
     * @return position 에 따른 ItemData
     */
    T get(int position);

    /**
     * 리스트 사이즈 반환 메소드
     *
     * @return 리스트 사이즈
     */
    int size();

    /**
     * 아이템 데이터 뷰 반환 메소드
     *
     * @return 아이템 뷰 개수
     */
    int itemViewCount();
}
