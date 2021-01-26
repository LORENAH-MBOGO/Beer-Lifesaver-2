package com.skylar.beer_lifesaver;

import androidx.fragment.app.Fragment;

public class BeerStyleDetailFragment extends Fragment {

    //    not using this for now...

//    @BindView(R.id.styleNameTextView)
//    TextView mStyleName;
//    @BindView(R.id.saveButton)
//    TextView mSaveButton;
//    @BindView(R.id.descriptionTextView)
//    TextView mDescription;
//    @BindView(R.id.abvTextView)
//    TextView mABV;
//    @BindView(R.id.srmTextView)
//    TextView mSRM;
//    @BindView(R.id.ibuTextView)
//    TextView mIBU;
//    @BindView(R.id.infoTextView)
//    TextView mInfoIcon;
//
//    private BeerStyle mBeerStyle;
//
//    public BeerStyleDetailFragment() {
//    }
//
//    public static BeerStyleDetailFragment newInstance(BeerStyle beerStyle) {
//        BeerStyleDetailFragment styleFragment = new BeerStyleDetailFragment();
//        Bundle args = new Bundle();
//        args.putParcelable("beerStyle", Parcels.wrap(beerStyle));
//        styleFragment.setArguments(args);
//        return styleFragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mBeerStyle = Parcels.unwrap(getArguments().getParcelable("beerStyle"));
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_beer_style_detail, container, false);
//        ButterKnife.bind(this, view);
//        return view;
//    }
}
