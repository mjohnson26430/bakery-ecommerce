class _GalleryAccessAppState extends State<GalleryAccessApp> {
  File? galleryFile;
  final imagePicker = ImagePicker();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Gallery and Camera Access"),
        backgroundColor: Colors.deepPurpleAccent,
        actions: [],
      ),
      body: Builder(
        builder: (BuildContext context) {
          return Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    _showPicker(context: context);
                  },
                  style: ButtonStyle(
                      backgroundColor: WidgetStateProperty.all(Colors.blue)),
                  child: const Text("Select image from Gallery and Camera"),
                ),
      const SizedBox(
                  height: 30,
              ),
          SizedBox(
                  height: 200,
                  width: 300,
                  child: galleryFile == null
                  ? const Center(child: Text("sorry nothing selected !!"))
                      : Center(child: Image.file(galleryFile!)))
            ],
          ),
        );
      },
    ),
  );
  }
    void _showPicker({required BuildContext context}) {
        showModalBottomSheet(
                context: context,
                builder: (BuildContext context) {
            return SafeArea(
                    child: Wrap(
                    children: [
            ListTile(
                    leading: const Icon(Icons.photo_library),
                    title: const Text('Photo Library'),
                    onTap: () {
                getImage(ImageSource.gallery);
                Navigator.of(context).pop();
            },
            ),
            ListTile(
                    leading: const Icon(Icons.photo_camera),
                    title: const Text('Camera'),
                    onTap: () {
                getImage(ImageSource.camera);
                Navigator.of(context).pop();
            },
            )
          ],
        ));
        });
    }
    Future getImage(ImageSource img) async {
        final pickedFile = await imagePicker.pickImage(source: img);
        XFile? xfilePath = pickedFile;
        setState(() {
            if (xfilePath != null) {
                galleryFile = File(pickedFile!.path);
            } else {
                ScaffoldMessenger.of(context)
                        .showSnackBar(const SnackBar(content: Text('Nothing is selected')));
            }
        });
    }
}